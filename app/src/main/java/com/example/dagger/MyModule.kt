package com.example.dagger

import android.util.Log
import dagger.Module
import dagger.Provides

/**
 * 通过模块自定义模块创建对象
 */
@Module
class MyModule {

    @Provides
    fun httpObject(): HttpObject {
        return HttpObject()
    }

    @Provides
    fun httpDb(): DbObject {
        return DbObject()
    }
}

class HttpObject {
    fun doHttp() {
        Log.i("doHttp", "doHttp method handle")
    }
}

class DbObject {
    fun doDb() {
        Log.i("doDb", "doDb method handle")
    }
}