package com.example.java.annotionstest;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/4/17
 */
public class Human {

    @MyAnnotation(value1 = "haha", value2 = "xixi")
    public void print(Integer age, String name) {
        System.out.println("human:{age =" + age + " ,name =" + name + "}");
    }
}
