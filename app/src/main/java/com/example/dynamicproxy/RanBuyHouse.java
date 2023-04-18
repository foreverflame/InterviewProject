package com.example.dynamicproxy;

import com.example.kotlin.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 买房子
 * @author: huangyonghuang
 * @date: 2023/4/18
 */
public class RanBuyHouse {

    public static void main(String[] args) {

//        InvocationHandler invocationHandler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                //在执行购买逻辑前可以做一些拦截，对于不符合的不予执行，返回false
//                if (method.getName().equals("buyHouse")) {
//                    System.out.println(args[0] + " will buy a house.");
//                }
//                //返回true，表示购买成功
//                return true;
//            }
//        };
//        IPersonBuyHouse iPersonBuyHouse = (IPersonBuyHouse) Proxy.newProxyInstance(IPersonBuyHouse.class.getClassLoader() //classLoader
//                , new Class[]{IPersonBuyHouse.class}, //传入要实现的接口
//                invocationHandler); //传入处理调用方法的InvocationHandler
//        iPersonBuyHouse.buyHouse("ryan");

        Ryan ryan = new Ryan();
        ryan.buyHouse("huang");

    }

}
