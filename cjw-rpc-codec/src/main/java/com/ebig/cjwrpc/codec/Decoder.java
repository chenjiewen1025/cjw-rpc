package com.ebig.cjwrpc.codec;

/**
 * ∑¥–Ú¡–ªØ
 */
public interface Decoder {
    <T> T decoder(byte[] bytes,Class<T> clazz);
}
