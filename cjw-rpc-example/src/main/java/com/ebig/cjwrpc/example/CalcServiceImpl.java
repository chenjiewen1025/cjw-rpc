package com.ebig.cjwrpc.example;

public class CalcServiceImpl implements  CalcService {
    @Override
    public int add(int a, int b) {
        return a+b;
    }
}
