package com.example.hilt.car

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.Request

@Module
@InstallIn(ActivityComponent::class)
object OkhttpModule {
    @Provides
    fun provideOkhttpClient(
        // Potential dependencies of this type
    ): OkHttpClient {
        return OkHttpClient()
    }
    @Provides
    fun providdeRequest(): Request {
        return Request.Builder()
            .get()
            .url("https://developer.android.google.cn/training/dependency-injection/hilt-android")
            .build()
    }
}