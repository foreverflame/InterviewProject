package com.example.java.annotionstest;


import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Persons.class)
public @interface Person {

    //注解只有属性，没有方法，显示形式和方法类似
    String role() default "person";


}
