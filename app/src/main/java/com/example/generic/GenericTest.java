package com.example.generic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * https://juejin.cn/post/6911302681583681544
 * https://zhuanlan.zhihu.com/p/131602691
 * @author: huangyonghuang
 * @date: 2023/4/13
 */
public class GenericTest {

    public static void main(String[] args) {
        genericTest();
    }

    private static void genericTest() {
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        int sum = (int) list.get(0) + (int) list.get(1);

//        List<? extends Animal> animals = new LinkedList<Cat>();
// 以下四行代码都不能编译通过
//        animals.add(new Dog());
//        animals.add(new Cat());
//        animals.add(new Animal());
//        animals.add(new Object());
// 可以添加null，但没意义
//        animals.add(null);
// 可以安全地取出来
//        Animal animal = animals.get(0);


        // 下面这行代码编译不通过
// List<? super Animal> animals = new LinkedList<Cat>();
// 下面都是OK的写法
// List<? super Animal> animals = new LinkedList<Object>();
// List<? super Animal> animals = new LinkedList<Animal>();
// 等价于上面一行的写法
        List<? super Animal> animals = new LinkedList<>();
        animals.add(new Cat());
        animals.add(new Dog());
// 取出来一定是Object
        Object object = animals.get(0);

// 这样写是OK的
        List<? super Cat> cats = new LinkedList<Animal>();
    }


}
