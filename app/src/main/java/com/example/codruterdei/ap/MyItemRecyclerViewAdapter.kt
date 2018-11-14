package com.example.codruterdei.ap

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.codruterdei.ap.models.PeopleModel

import kotlinx.android.synthetic.main.fragment_item.view.*

class MyItemRecyclerViewAdapter(
    var mValues: List<PeopleModel>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.Username
        holder.tvIsHome.text = item.Distance.toString()

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.tvName
        val tvIsHome: TextView = mView.tvIsHome

        override fun toString(): String {
            return super.toString() + " '" + tvIsHome.text + "'"
        }
    }
}
