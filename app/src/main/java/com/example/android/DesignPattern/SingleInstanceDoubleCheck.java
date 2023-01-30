package com.example.android.DesignPattern;

public class SingleInstanceDoubleCheck {

    private volatile static SingleInstanceDoubleCheck instance = null;

    private SingleInstanceDoubleCheck() {
    }

    public static SingleInstanceDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingleInstanceDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingleInstanceDoubleCheck();
                }
            }
        }
        return instance;
    }
}