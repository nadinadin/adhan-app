package com.example.tristan.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView

class MainActivity : AppCompatActivity() {
    private var mGridView: GridView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myGridView: GridView? = findViewById(R.id.times_grid_view)
        val recipeList: Array<Work> = Array<Work>(20, {i ->
            val task = when (i % 4) {
                0 -> "eat"
                1 -> "pray"
                2 -> "love"
                else -> "program"
            }
            Work(task)
        })

        val listItems: Array<String> = Array(recipeList.size, {i -> recipeList[i].getThing()})
        val adapterA = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        val adapterW = WorkAdapter(this, recipeList)
        myGridView!!.adapter = adapterW
        mGridView = myGridView
    }
}
