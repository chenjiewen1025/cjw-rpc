package com.ebig.cjwrpc.codec;

/**
 * �����л�
 */
public interface Decoder {
    <T> T decoder(byte[] bytes,Class<T> clazz);
}
