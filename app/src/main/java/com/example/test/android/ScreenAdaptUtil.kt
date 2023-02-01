package com.example.test.android

import android.app.Activity

/**
 * @description: 头条屏幕适配工具
 * @author: huangyonghuang
 * @date: 2023/2/1
 */
object ScreenAdaptUtil {

    fun setCustomDensity(activity: Activity) {
        activity.resources.displayMetrics.apply {
            val targetDensity = (widthPixels / 360).toFloat()
            density = targetDensity
            scaledDensity = targetDensity
            densityDpi = 160 * targetDensity.toInt()
        }
    }
}