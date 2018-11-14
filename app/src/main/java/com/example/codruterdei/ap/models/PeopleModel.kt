package com.example.codruterdei.ap.models

import android.util.Log
import com.example.codruterdei.ap.helper.fromJson
import org.json.JSONArray

data class PeopleModel(val Username: String, val Distance: Float)

class AnswerPeopleModel(val json: String) {
    var items = listOf<PeopleModel>()
    init {
        populate()
    }


    private fun populate() {
        val root = JSONArray(json)
        Log.d("json", json)

        val tempItems = ArrayList<PeopleModel>()
        repeat(root.length()) {
            val post = root.getJSONObject(it)
            val tempPerson = fromJson<PeopleModel>(post.toString())

            tempItems.add(tempPerson)
        }


        items = tempItems.toList()
        println(items)

    }
}
