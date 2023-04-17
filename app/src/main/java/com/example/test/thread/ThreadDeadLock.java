package com.example.test.thread;

/**
 * @description:  https://blog.csdn.net/m0_52042041/article/details/113243568
 * @author: huangyonghuang
 * @date: 2023/4/17
 */
public class ThreadDeadLock {

    private static final Object obj1 = new Object();
    private static final Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(ThreadDeadLock::test1, "t1").start();
        new Thread(ThreadDeadLock::test2, "t2").start();
    }


    private static void test1() {
        try {
            synchronized (obj1) {
                Thread.sleep(100);
                synchronized (obj2) {
                    System.out.println("test1 ob2");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void test2() {
        try {
            synchronized (obj2) {
                Thread.sleep(100);
                synchronized (obj1) {
                    System.out.println("test2 obj2");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
