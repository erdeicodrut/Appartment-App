package com.example.codruterdei.ap

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_start.*
import android.telephony.TelephonyManager
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import java.io.IOException
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.EditText
import android.content.Intent




class StartActivity : AppCompatActivity() {


    @SuppressLint("HardwareIds", "LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)



        val sharedPref = this.getSharedPreferences(
            getString(R.string.name), Context.MODE_PRIVATE
        )

        if (sharedPref.contains("name")) { startActivity(Intent(this, MainActivity::class.java)) }

        reqPermission(Manifest.permission.READ_PHONE_STATE)
        reqPermission(Manifest.permission.INTERNET)
        reqPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        reqPermission(Manifest.permission.ACCESS_COARSE_LOCATION)

        btnSave.setOnClickListener {

            doAsync {

                if (ContextCompat.checkSelfPermission(this@StartActivity, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this@StartActivity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    Log.i(
                        "ABCDEF",
                        "http://${getString(R.string.url)}/register/${(getSystemService(Context.TELEPHONY_SERVICE)as TelephonyManager).deviceId}/${tbName.text}"
                    )

                    run("http://${getString(R.string.url)}/register/${(getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId}/${tbName.text}")

                    sharedPref.edit().putString("name", tbName.text.toString()).apply()
                }
            }


        }
    }

    private fun reqPermission(permission: String) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission

            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    permission
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permission),
                    1
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    var client = OkHttpClient()

    @Throws(IOException::class)
    fun run(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }
}