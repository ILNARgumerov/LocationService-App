package com.example.locationserviceapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceButton.setOnClickListener {
            startService(Intent(this, LocationService::class.java))
        }
        stopServiceButton.setOnClickListener {
            stopService(Intent(this, LocationService::class.java))
        }
    }
}