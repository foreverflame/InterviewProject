package com.example.kotlin

/**
 * @description:高阶函数训练
 * @author: huangyonghuang
 * @date: 2023/1/9
 */
class HigherFunction {

    fun main() {
        check(10) { 10 }
    }

    fun check(num: Int, add: () -> Int): Int {
        return num + add()
    }

}