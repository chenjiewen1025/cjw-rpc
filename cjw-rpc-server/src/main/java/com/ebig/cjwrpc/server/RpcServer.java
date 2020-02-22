package com.ebig.cjwrpc.server;

import com.ebig.cjwrpc.Request;
import com.ebig.cjwrpc.Response;
import com.ebig.cjwrpc.codec.Decoder;
import com.ebig.cjwrpc.codec.Encoder;
import com.ebig.cjwrpc.common.utils.ReflectionUtils;
import com.ebig.cjwrpc.transport.RequestHandler;
import com.ebig.cjwrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {

    private  RpcServerConfig config;

    private TransportServer net;

    private Encoder encoder;

    private Decoder decoder;

    private ServiceManager serviceManager;

    private ServiceInvoker serviceInvoker;

    public RpcServer(){
         this(new RpcServerConfig());
    }
    public RpcServer(RpcServerConfig config){
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncodeClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecodeClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();

    }

    public void start(){
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }

    public <T> void register(Class<T> interfaceClass,T bean){
        serviceManager.register(interfaceClass,bean);
    }

    public RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream in, OutputStream out) {
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(in,in.available());
                Request req = decoder.decoder(inBytes,Request.class);
                ServiceInstance ins = serviceManager.lookup(req);
                Object returnObj =  serviceInvoker.invoke(ins,req);
                resp.setData(returnObj);
            } catch (IOException e) {
                log.error(e.getMessage(),e);
                resp.setCode(1);
                resp.setMessage(e.getMessage());
            }finally {
                byte[] outByte = encoder.encoder(resp);
                try {
                    out.write(outByte);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
