package com.example.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Ryan implements IPersonBuyHouse {
   // buyHouse的真正逻辑在这个匿名内部类的invoke方法中
   private InvocationHandler handler = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            // 被代理类真正执行的逻辑
            if (method.getName().equals("buyHouse")) {
                System.out.println(args[0] + " will buy a house.");
            }
            return true;
        }
    };

    @Override
    public boolean buyHouse(String name) {
        try {
            // 反射获取buyHouse这个Method
            Method buyHouseMethod = IPersonBuyHouse.class.getDeclaredMethod("buyHouse", String.class);
            // 将buyHouse的参数封装成一个数组
            Object[] params = new Object[1];
            params[0] = name;
            // 实际调用了InvocationHandler的invoke方法
            return (boolean) handler.invoke(this, buyHouseMethod, params);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }
}