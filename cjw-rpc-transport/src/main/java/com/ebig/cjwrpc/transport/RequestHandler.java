package com.ebig.cjwrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

public interface RequestHandler {

    void onRequest(InputStream in, OutputStream out);
}
