package com.example.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 三个线程顺序打印ABC
 * @author: huangyonghuang
 * @date: 2023/2/10
 */
public class PrintABCUsingLock {

    private int times; //控制打印次数
    private int state; //当前状态值：保证三个线程间交替打印
    private Lock lock = new ReentrantLock();

    PrintABCUsingLock(int times) {
        this.times = times;
    }

    private void printLetter(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.println(name);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABCUsingLock printABCUsingLock = new PrintABCUsingLock(10);
        new Thread(() -> {
            printABCUsingLock.printLetter("A", 0);
        }).start();
        new Thread(() -> {
            printABCUsingLock.printLetter("B", 1);
        }).start();
        new Thread(() -> {
            printABCUsingLock.printLetter("C", 2);
        }).start();
    }
}
