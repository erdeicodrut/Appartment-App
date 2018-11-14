package com.example.codruterdei.ap

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.codruterdei.ap.helper.HTTP_GET
import com.example.codruterdei.ap.models.AnswerPeopleModel
import com.example.codruterdei.ap.models.PeopleModel

class ItemFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    lateinit var itemsInList: List<PeopleModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                adapter = MyItemRecyclerViewAdapter(listOf())

                val mainHandler = Handler(context.mainLooper)



                HTTP_GET("http://${getString(R.string.url)}/users/online") {
                    itemsInList = AnswerPeopleModel(it).items

                    itemsInList.forEach {
                        Log.e( "Items in list", it.Username)
                    }

                    (adapter as MyItemRecyclerViewAdapter).mValues = itemsInList

                    mainHandler.post {
                        (adapter as MyItemRecyclerViewAdapter).notifyDataSetChanged()
                    }
                }

            }
        }
        return view
    }

}
