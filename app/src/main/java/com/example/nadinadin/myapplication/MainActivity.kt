package com.example.nadinadin.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.nadinadin.prayertimes.Settings
import com.example.nadinadin.prayertimes.calculatePrayerTimes
import com.example.nadinadin.prayertimes.getAdhanName
import com.example.nadinadin.prayertimes.getNextAdhan
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val upcomingAdhan: TextView = findViewById(R.id.upcomingAdhan)
        val times = calculatePrayerTimes(Date(), Settings(0.0, 0.0, 0.0))
        val jhh = getNextAdhan(Date(),times)
        upcomingAdhan.text = getAdhanName(jhh)

        }
}
