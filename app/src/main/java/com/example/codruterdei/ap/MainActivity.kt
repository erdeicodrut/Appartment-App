package com.example.codruterdei.ap

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import android.util.Log
import com.example.codruterdei.ap.helper.HTTP_GET
import com.example.codruterdei.ap.helper.getLastBestLocation

import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {


    @SuppressLint("MissingPermission", "HardwareIds")
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun logLocation() {
        val homeLocation = Location("")
        homeLocation.latitude = 46.753991
        homeLocation.longitude = 23.560013

        HTTP_GET(
            "http://${getString(R.string.url)}/users/update/${(getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId}/${getLastBestLocation(
                this
            ).distanceTo(homeLocation)}"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



    }
}
