package com.ebig.cjwrpc.client;

import com.ebig.cjwrpc.Peer;
import com.ebig.cjwrpc.transport.TransportClient;
import com.ebig.cjwrpc.transport.TransportServer;

import java.util.List;

/**
 * 选择哪个server去连接
 */
public interface TransportSelector {

    /*
    初始化selector

     */
    void init(List<Peer> peers,int count,Class<? extends TransportClient> clazz);

    /*
    选择client与server做交互
     */
    TransportClient select();

    /**
     * 释放
     * @param client
     */
    void release(TransportClient client);

    void close();



}
