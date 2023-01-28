package com.example.java;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            System.out.println("hello world！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(" ===> main Thread execute here ! " );
    }
}
