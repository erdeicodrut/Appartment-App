package com.example.codruterdei.ap.helper

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.support.v4.content.ContextCompat.getSystemService




@SuppressLint("MissingPermission")
public fun getLastBestLocation(context: Context): Location {
    val mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    val locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

    var GPSLocationTime: Long = 0
    if (null != locationGPS) {
        GPSLocationTime = locationGPS.time
    }

    var NetLocationTime: Long = 0

    if (null != locationNet) {
        NetLocationTime = locationNet.time
    }

    return if (0 < GPSLocationTime - NetLocationTime) {
        locationGPS
    } else {
        locationNet
    }
}
