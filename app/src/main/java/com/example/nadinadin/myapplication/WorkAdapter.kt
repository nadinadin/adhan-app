package com.example.nadinadin.myapplication

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class WorkAdapter(private val mContext: Context, private val thingsToDo: Array<Work>) : BaseAdapter() {

    // 2
    override fun getCount(): Int {
        return thingsToDo.size
    }

    // 3
    override fun getItemId(position: Int): Long {
        return 0
    }

    // 4
    override fun getItem(position: Int): Work? {
        return null
    }

    // 5
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val dummyTextView = TextView(mContext)
        dummyTextView.text = thingsToDo[position].getThing()

        return dummyTextView
    }
}