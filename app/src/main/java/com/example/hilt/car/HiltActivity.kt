package com.example.hilt.car

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.widget.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/4/19
 */
@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {
    @Inject
    lateinit var car: Car//这里必须使用lateinit延迟初始化才会有效
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        car.drive()
    }
}