package com.example.codruterdei.ap.helper

import com.squareup.moshi.Moshi


inline fun <reified T> toJson(toConvert: T): String {
	val moshi = Moshi.Builder().build()
	
	val jsonAdapter = moshi.adapter(T::class.java)
	
	return jsonAdapter.toJson(toConvert)
}

inline fun <reified T> fromJson(toConvert: String): T {
	val moshi = Moshi.Builder().build()
	val jsonAdapter = moshi.adapter<T>(T::class.java)
	
	val currObject = jsonAdapter.fromJson(toConvert)
	return currObject!!
}
