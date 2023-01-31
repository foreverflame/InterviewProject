package com.example.test;

/**
 * @description: 双重锁单例、静态内部类单例
 * @author: huangyonghuang
 * @date: 2023/1/31
 */
public class SingleInstance {

    private SingleInstance() {
    }

    private static volatile SingleInstance singleInstance = null;

    public static SingleInstance getInstance() {
        if (singleInstance == null) {
            synchronized (SingleInstance.class) {
                if (singleInstance == null) {
                    singleInstance = new SingleInstance();
                }
            }
        }
        return singleInstance;
    }


    private static class InnerSingleInstance {
        private static final SingleInstance INSTANCE = new SingleInstance();
    }

    public static SingleInstance getSingleInstance() {
        return InnerSingleInstance.INSTANCE;
    }

}
