package com.example.hilt.car

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/4/19
 */
@Module//必须配置的注解，表示这个对象是Module的配置规则
@InstallIn(ActivityComponent::class)//表示这个module中的配置是用来注入到Activity中的
abstract class DriverModule {
    //形参中的DriverImple表示真实要注入Car构造方法中的Driver实现，返回值Driver表示DriverImple所实现的抽象接口
    @Binds
    abstract fun bindDriver(driver: DriverImpl): Driver
}