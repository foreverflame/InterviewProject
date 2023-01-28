package com.example.java.annotionstest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Persons {

    //注解属性
    Person[] value();
}
