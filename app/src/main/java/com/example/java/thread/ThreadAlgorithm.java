package com.example.java.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程测试  https://www.jianshu.com/p/41ab99e6ac2c 多线程打奇偶
 */
public class ThreadAlgorithm {

    private Semaphore sea_first_two = new Semaphore(0);

    private Semaphore sea_two_second = new Semaphore(0);


    ThreadAlgorithm() {

    }


    private void runFirst(Runnable first) throws InterruptedException {
        first.run();
        sea_first_two.release();

    }


    private void runSecond(Runnable second) throws InterruptedException {
        sea_first_two.acquire();
        second.run();
        sea_two_second.release();

    }

    private void runThird(Runnable thirid) throws InterruptedException {
        sea_two_second.acquire();
        thirid.run();


    }

    public static class Foo {

        private AtomicInteger firstJobDone = new AtomicInteger(0);
        private AtomicInteger secondJobDone = new AtomicInteger(0);

        public Foo() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first".
            printFirst.run();
            // mark the first job as done, by increasing its count.
            firstJobDone.incrementAndGet();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (firstJobDone.get() != 1) {
                // waiting for the first job to be done.
            }
            // printSecond.run() outputs "second".
            printSecond.run();
            // mark the second as done, by increasing its count.
            secondJobDone.incrementAndGet();
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (secondJobDone.get() != 1) {
                // waiting for the second job to be done.
            }
            // printThird.run() outputs "third".
            printThird.run();
        }
    }


}
