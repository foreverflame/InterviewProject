package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlin.inflate
import com.example.widget.databinding.ActivityMain2Binding


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val activityMain2Binding: ActivityMain2Binding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMain2Binding.apply {
            tvCoroutineStart.setOnClickListener {
            }
        }

        //glide
        Glide.with(this).load("").into(activityMain2Binding.image)
    }

}







