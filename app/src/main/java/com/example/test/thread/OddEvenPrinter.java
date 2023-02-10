package com.example.test.thread;

/**
 * @description: 两个线程打印奇偶
 * @author: huangyonghuang
 * @date: 2023/2/10
 */
public class OddEvenPrinter {

    private final Object object = new Object();
    private final int limit;
    private volatile int count;

    OddEvenPrinter(int count, int limit) {
        this.count = count;
        this.limit = limit;
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter(0, 10);
        new Thread(printer::print, "A").start();
        new Thread(printer::print, "B").start();
    }

    private void print() {
        synchronized (object) {
            while (count < limit) {
                try {
                    System.out.println(String.format("线程[%s]打印%d", Thread.currentThread().getName(), ++count));
                    object.notifyAll();
                    object.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            object.notifyAll(); //防止阻塞主线程
        }
    }
}
