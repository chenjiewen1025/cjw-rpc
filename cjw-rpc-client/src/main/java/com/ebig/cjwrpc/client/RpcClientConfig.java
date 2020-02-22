package com.ebig.cjwrpc.client;

import com.ebig.cjwrpc.Peer;
import com.ebig.cjwrpc.codec.Decoder;
import com.ebig.cjwrpc.codec.Encoder;
import com.ebig.cjwrpc.codec.JSONDecode;
import com.ebig.cjwrpc.codec.JSONEncode;
import com.ebig.cjwrpc.transport.HTTPTransportClient;
import com.ebig.cjwrpc.transport.HTTPTransportServer;
import com.ebig.cjwrpc.transport.TransportClient;
import com.ebig.cjwrpc.transport.TransportServer;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {


    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;

    private Class<? extends Encoder> encodeClass = JSONEncode.class;

    private Class<? extends Decoder> decodeClass = JSONDecode.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    int connectCount = 1;

    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",3000));
}
