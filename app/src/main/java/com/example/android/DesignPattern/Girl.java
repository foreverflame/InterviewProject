package com.example.android.DesignPattern;


public class Girl implements Observer {

    String name;

    public Girl(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "收到快递," + message + "叫男朋友去取快递");

    }
}
