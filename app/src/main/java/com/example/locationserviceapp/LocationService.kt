package com.example.locationserviceapp

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.location.Location
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService

class LocationService : LifecycleService() {

    private lateinit var notificationManager: NotificationManager
    private val checkLocationChange: (Location) -> Unit = ::checkLocationChange

    private fun checkLocationChange(l: Location) {
        if ("${l.latitude} ${l.longitude}" != "${LocationData.location.value?.latitude} ${LocationData.location.value?.longitude}") {
            LocationData.location.value = l
            sendNotification()
        }
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
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

    private fun sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "id", "My channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "My channel description"
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, "id")
            .setSmallIcon(R.drawable.ic_btn_speak_now)
            .setContentTitle("Location")
            .setContentText("Your Location has been changed:")
        val notification = builder.build()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }
}