package com.ebig.cjwrpc.server;

import com.ebig.cjwrpc.codec.Decoder;
import com.ebig.cjwrpc.codec.Encoder;
import com.ebig.cjwrpc.codec.JSONDecode;
import com.ebig.cjwrpc.codec.JSONEncode;
import com.ebig.cjwrpc.transport.HTTPTransportServer;
import com.ebig.cjwrpc.transport.TransportServer;
import lombok.Data;

@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encodeClass = JSONEncode.class;
    private Class<? extends Decoder> decodeClass = JSONDecode.class;
    private int port  = 3000;
}
