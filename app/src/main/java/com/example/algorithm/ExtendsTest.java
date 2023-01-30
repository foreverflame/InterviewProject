package com.example.algorithm;

public class ExtendsTest {

    public static void main(String[] args) {
        Son son = new Son();
    }

    public static class Father {

        Father() {
            System.out.println("父类构造方法");
        }

        static {
            System.out.println("父类静态代码块");
        }

        {
            System.out.println("父类代码块");
        }

        public void speak() {
            System.out.println("父类一般方法");
        }
    }

    public static class Son extends Father {

        Son() {
            System.out.println("子类构造方法");
        }

        static {
            System.out.println("子类静态代码块");
        }

        {
            System.out.println("子类代码块");
        }

        @Override
        public void speak() {
            System.out.println("子类一般方法");
        }

    }
}
