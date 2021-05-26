package com.example.locationserviceapp

import android.content.Intent
import android.location.Location
import androidx.lifecycle.LifecycleService

class LocationService : LifecycleService() {


    private val checkLocationChange: (Location) -> Unit = ::checkLocationChange

    private fun checkLocationChange(l: Location) {
        if ("${l.latitude} ${l.longitude}" != "${LocationData.location.value?.latitude} ${LocationData.location.value?.longitude}")
            LocationData.location.value = l
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val command = intent?.getStringExtra("command")

        if (command == "start") LocationHelper.getInstance()
            .startLocationListening(this.applicationContext, checkLocationChange)
        else {
            stopService(intent)
            LocationHelper.getInstance().stopLocating()
        }

        return super.onStartCommand(intent, flags, startId)
    }
}