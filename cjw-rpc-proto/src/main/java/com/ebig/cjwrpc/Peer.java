package com.ebig.cjwrpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ��ʾ���紫���һ���˵�
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}

