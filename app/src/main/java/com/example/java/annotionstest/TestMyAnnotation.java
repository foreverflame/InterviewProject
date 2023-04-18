package com.example.java.annotionstest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @description: 通过反射测试注解
 * @author: huangyonghuang
 * @date: 2023/4/17
 */
public class TestMyAnnotation {

    public static void main(String[] args) throws Exception {
        Human human = new Human();
        //通过反射获取方法
        Method method = human.getClass().getMethod("print", Integer.class, String.class);
        //执行对应的print方法
        method.invoke(human, 11, "小明");
        //从方法中获取注解
        getAnnotationByMethod(method);

    }

    private static void getAnnotationByMethod(Method method) {
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            String value1 = annotation.value1();
            String value2 = annotation.value2();
            System.out.println("human:{value1 =" + value1 + " ,value2 =" + value2 + "}");
        }
        for (Annotation annotation : method.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
