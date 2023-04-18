package com.example

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            //用于打印内存泄漏的子进程
            return
        }
        // 在这里写你app的初始化代码
        LeakCanary.install(this)
    }
}