package com.example.android.DesignPattern;

public class SingleInstanceTest {

    private SingleInstanceTest() {
    }

    private static class singleInstanceHolder {
        private static SingleInstanceTest singleInstanceTest = new SingleInstanceTest();
    }

    public static SingleInstanceTest getInstance() {
        return singleInstanceHolder.singleInstanceTest;
    }

}