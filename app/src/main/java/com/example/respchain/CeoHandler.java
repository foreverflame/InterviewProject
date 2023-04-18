package com.example.respchain;

public class CeoHandler implements Handler {
    @Override
    public Boolean process(Request request) {
        // 对Bob有偏见，Bob的请求直接拒绝
        return !request.getName().equals("Bob");
    }
}