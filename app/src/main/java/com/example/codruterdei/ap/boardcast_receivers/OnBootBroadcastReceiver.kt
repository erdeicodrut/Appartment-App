package com.example.codruterdei.ap.boardcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.codruterdei.ap.services.LocationService

class OnBootBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val i = Intent(context, LocationService::class.java)
            context.startForegroundService(i)
        }
    }
}
