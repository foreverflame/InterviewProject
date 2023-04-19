package com.example.hilt.car

import javax.inject.Inject

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/4/19
 */
class DriverImpl @Inject constructor() : Driver {//因为DriverImpl需要被注入到Car的构造中，所以DriverImpl本身一个是被注入着，他的构造中也需要使用@Inject注解
override val name: String
    get() = "mage"
}