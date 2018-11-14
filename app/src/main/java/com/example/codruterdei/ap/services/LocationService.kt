package com.example.codruterdei.ap.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import com.example.codruterdei.ap.logLocation
import java.util.*


class LocationService : Service() {

    override fun onCreate() {
        val pendingIntent: PendingIntent =
            Intent(this, LocationService::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "location_logger"
        val channelName = "Location logger"
        val importance = NotificationManager.IMPORTANCE_LOW
        val notificationChannel = NotificationChannel(channelId, channelName, importance)
        notificationManager.createNotificationChannel(notificationChannel)


        val notification: Notification = Notification.Builder(this, channelId)
            .setContentTitle("Apartment")
            .setContentText("Logging GPS location")
//                .setSmallIcon(R.drawable.icon)
            .setContentIntent(pendingIntent)
//                .setTicker(getText(R.string.ticker_text))
            .build()

        startForeground(42, notification)


        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                logLocation(this@LocationService)
            }
        }, 0, 5 * 60 * 1000)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}
