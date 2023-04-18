package com.example.hilt

import javax.inject.Inject

class AnalyticsAdapter @Inject constructor(
    private val service: AnalyticsService
) {}