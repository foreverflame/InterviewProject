package com.example.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * https://developer.android.google.cn/training/dependency-injection/hilt-android?hl=zh-cn#kotlin
 */
@AndroidEntryPoint
class ExampleActivity : AppCompatActivity() {

    @Inject
    lateinit var analytics: AnalyticsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}