package com.example.locationserviceapp

import android.content.Intent
import androidx.lifecycle.LifecycleService

class LocationService : LifecycleService() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
}