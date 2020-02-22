package com.ebig.cjwrpc.example;

import com.ebig.cjwrpc.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        System.out.println(service.add(1,2));

    }
}
