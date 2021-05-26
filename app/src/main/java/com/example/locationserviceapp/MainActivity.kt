package com.example.locationserviceapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceButton.setOnClickListener {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                requestLocationPermission()
        }
        stopServiceButton.setOnClickListener {
            isRunning = false
            changeServiceState()
        }
    }

    private fun requestLocationPermission() {
        this.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0
            && permissions.first() == Manifest.permission.ACCESS_FINE_LOCATION
            && grantResults.first() == PackageManager.PERMISSION_GRANTED
        ) {
            isRunning = true
            changeServiceState()
        }
    }

    private fun changeServiceState() {
        if (isRunning)
            sendCommand("start")
        else
            sendCommand("stop")
    }

    private fun sendCommand(command: String) {
        val intent = Intent(this, LocationService::class.java)
        intent.putExtra("command", command)
        startService(intent)
    }
}