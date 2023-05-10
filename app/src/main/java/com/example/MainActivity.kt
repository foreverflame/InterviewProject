package com.example

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.example.kotlin.TestFlowViewModel
import com.example.kotlin.inflate
import com.example.widget.databinding.ActivityMain2Binding
import com.example.workmanager.UploadWorker


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val activityMain2Binding: ActivityMain2Binding by inflate()
    private val testFlowViewModel: TestFlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMain2Binding.apply {
            tvCoroutineStart.setOnClickListener {
            }
        }

        //glide
        Glide.with(this).load("").into(activityMain2Binding.image)

        //WorkManager
        val request = OneTimeWorkRequest.Builder(UploadWorker::class.java).build()
        WorkManager.getInstance(this).enqueue(request)


    }

}







