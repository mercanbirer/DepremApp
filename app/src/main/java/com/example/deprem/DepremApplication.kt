package com.example.deprem

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DepremApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        timber.log.Timber.plant(timber.log.Timber.DebugTree())
    }
}