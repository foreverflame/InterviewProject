package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @description: workmanager https://www.bilibili.com/video/BV1Cf4y1u7eY?p=5&vd_source=6df230978ac4c6c9050f1c62d21767a5  视频
 * @author: huangyonghuang
 * @date: 2023/5/10
 */
class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    companion object {
        private const val TAG = "UploadWorker"
    }

    override fun doWork(): Result {
        Thread.sleep(2000)
        Log.e(TAG, "上传成功")
        return Result.success()
    }
}