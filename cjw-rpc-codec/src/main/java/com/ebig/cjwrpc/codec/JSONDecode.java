package com.ebig.cjwrpc.codec;

import com.alibaba.fastjson.JSON;

public class JSONDecode implements Decoder {
    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
