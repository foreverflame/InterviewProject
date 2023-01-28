package com.example.android.DesignPattern;


public class Boy implements Observer {

    String name;

    public Boy(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name+"收到快递,"+message+"取快递");

    }
}
