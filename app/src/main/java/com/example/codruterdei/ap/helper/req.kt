package com.example.codruterdei.ap.helper

import android.util.Log
import com.squareup.moshi.Json
import okhttp3.*
import java.io.IOException

fun HTTP_GET(URL: String, jsonBody: String = "", ending: (String) -> Unit = {}): Boolean {

//	val JSON = MediaType.parse("application/json; charset=utf-8")

	val req = Request.Builder()
			.url(URL)
//			.post(reqBody)
			.build()
	
	val client = OkHttpClient()
	
	client.newCall(req).enqueue(object : Callback {
		override fun onFailure(call: Call?, e: IOException?) {
			println("O PUSCAT \n ${e.toString()} \n $URL \n ${call.toString()}")
		}
		
		override fun onResponse(call: Call, response: Response) {
			ending(response.body()!!.string())
		}
	})
	
	return true
	
	
}
	
	

