package com.ebig.cjwrpc.client;

import com.ebig.cjwrpc.codec.Decoder;
import com.ebig.cjwrpc.codec.Encoder;
import com.ebig.cjwrpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

public class RpcClient {

    private RpcClientConfig config;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector selector;

    public RpcClient(){
       this(new RpcClientConfig());
    }
    public RpcClient(RpcClientConfig config){
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(config.getEncodeClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecodeClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());
        this.selector.init(this.config.getServers(),
                this.config.getConnectCount(),this.config.getTransportClass());

    }

    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{clazz},new RemoteInvoker(clazz,encoder,decoder,selector));
    }
}
