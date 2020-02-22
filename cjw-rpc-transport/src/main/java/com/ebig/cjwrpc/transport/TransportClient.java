package com.ebig.cjwrpc.transport;

import com.ebig.cjwrpc.Peer;

import java.io.InputStream;

public interface TransportClient {

    void connect(Peer peer);

    void close();

    InputStream write(InputStream data);
}
