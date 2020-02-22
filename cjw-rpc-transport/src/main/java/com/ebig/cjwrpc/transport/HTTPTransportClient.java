package com.ebig.cjwrpc.transport;

import com.ebig.cjwrpc.Peer;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPTransportClient implements TransportClient {

    private String url;

    private static Logger log = LoggerFactory.getLogger(HTTPTransportClient.class);

    @Override
    public void connect(Peer peer) {
        this.url = "http://"+peer.getHost()+":"+peer.getPort();
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpconn = (HttpURLConnection) new URL(this.url).openConnection();
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);
            httpconn.setUseCaches(false);
            httpconn.setRequestMethod("POST");
            httpconn.connect();
            IOUtils.copy(data,httpconn.getOutputStream());
            int code = httpconn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK){
                return httpconn.getInputStream();
            }else {
                return httpconn.getErrorStream();
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            throw new IllegalStateException(e);
        }
    }
}
