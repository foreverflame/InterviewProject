package com.example.java.thread;


/**
 * https://blog.csdn.net/m0_52042041/article/details/113243568
 * 线程死锁产生的原因
 */
public class DeadLock {

    private static final Object obj1 = new Object();
    private static final Object obj2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                synchronized (obj1) {
                    Thread.sleep(100);
                    synchronized (obj2) {
                        System.out.println("t1");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                synchronized (obj2) {
                    Thread.sleep(100);
                    synchronized (obj1) {
                        System.out.println("t2");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
