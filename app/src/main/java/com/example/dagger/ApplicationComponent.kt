package com.example.dagger

import dagger.Component

@Component
internal interface ApplicationComponent {
    fun injectLoginActivity(activity: LoginActivity)
}