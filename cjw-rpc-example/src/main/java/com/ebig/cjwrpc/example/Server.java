package com.ebig.cjwrpc.example;

import com.ebig.cjwrpc.server.RpcServer;
import com.ebig.cjwrpc.server.RpcServerConfig;

public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class,new CalcServiceImpl());
        server.start();

    }
}
