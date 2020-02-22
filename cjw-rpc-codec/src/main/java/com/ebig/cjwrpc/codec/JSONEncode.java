package com.ebig.cjwrpc.codec;

import com.alibaba.fastjson.JSON;

public class JSONEncode implements Encoder {
    @Override
    public byte[] encoder(Object object) {
        return JSON.toJSONBytes(object);
    }
}
