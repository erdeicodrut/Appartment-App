package com.example.codruterdei.ap

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import com.example.codruterdei.ap.helper.HTTP_GET
import com.example.codruterdei.ap.helper.getLastBestLocation
import kotlinx.android.synthetic.main.activity_main.*


@SuppressLint("HardwareIds")
fun logLocation(context: Context) {
    val homeLocation = Location("")
    homeLocation.latitude = 46.753991
    homeLocation.longitude = 23.560013

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.INTERNET
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        HTTP_GET(

            "http://${context.getString(R.string.url)}/users/update/${(ContextCompat.getSystemService(
                context,
                TelephonyManager::class.java
            ) as TelephonyManager).deviceId}/${getLastBestLocation(
                context
            ).distanceTo(homeLocation)}"
        )
    }
}


class MainActivity : AppCompatActivity() {


    @SuppressLint("MissingPermission", "HardwareIds")
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                VPager.setCurrentItem(0, true)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                VPager.setCurrentItem(1, true)


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                VPager.setCurrentItem(2, true)


                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        VPager.adapter = VPAdapter(supportFragmentManager)
        VPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                navigation.selectedItemId = when (position) {
                    0 -> R.id.navigation_home
                    1 -> R.id.navigation_dashboard
                    2 -> R.id.navigation_notifications

                    else -> R.id.navigation_home
                }
            }

        })


    }

    inner class VPAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> {
                    ItemFragment()
                }
                1 -> {
                    ItemFragment()
                }
                2 -> {
                    ItemFragment()
                }

                else -> Fragment()

            }


        override fun getCount(): Int = 3
    }

}

