package com.ebig.cjwrpc.client;

import com.ebig.cjwrpc.Request;
import com.ebig.cjwrpc.Response;
import com.ebig.cjwrpc.ServiceDescriptor;
import com.ebig.cjwrpc.codec.Decoder;
import com.ebig.cjwrpc.codec.Encoder;
import com.ebig.cjwrpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务代理类
 */
public class RemoteInvoker implements InvocationHandler {

    private Encoder encoder;

    private Decoder decoder;

    private Class clazz;

    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder,
                         Decoder decoder,TransportSelector selector){
        this.decoder = decoder;
        this.encoder = encoder;
        this.selector = selector;
        this.clazz = clazz;
    };

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);
        Response resp = invokeRemote(request);
        if (resp.getCode() != 0){
            throw new IllegalStateException("fail to invokeremote");
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request){
        TransportClient client = null;
        Response resp = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encoder(request);
            InputStream recipe =  client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(recipe,recipe.available());
            resp = decoder.decoder(inBytes,Response.class);


        }catch (Exception e){
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("invokeRemote error");
        }finally {
            if (client != null){
                selector.release(client);
            }
        }

        return resp;
    }
}
