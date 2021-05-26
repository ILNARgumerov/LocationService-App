package com.example.locationserviceapp

import android.location.Location
import androidx.lifecycle.MutableLiveData

object LocationData {
    val location: MutableLiveData<Location> = MutableLiveData()
}