package com.ebig.cjwrpc.client;

import com.ebig.cjwrpc.Peer;
import com.ebig.cjwrpc.transport.TransportClient;
import com.ebig.cjwrpc.transport.TransportServer;

import java.util.List;

/**
 * ѡ���ĸ�serverȥ����
 */
public interface TransportSelector {

    /*
    ��ʼ��selector

     */
    void init(List<Peer> peers,int count,Class<? extends TransportClient> clazz);

    /*
    ѡ��client��server������
     */
    TransportClient select();

    /**
     * �ͷ�
     * @param client
     */
    void release(TransportClient client);

    void close();



}
