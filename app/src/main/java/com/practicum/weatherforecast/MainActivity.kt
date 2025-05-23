package com.practicum.weatherforecast

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val list = mutableListOf<WeatherDaily>().apply {
            add(WeatherDaily(Day.Monday, -4))
            add(WeatherDaily(Day.Thuesday, -45))
            add(WeatherDaily(Day.Wednesday, 0))
            add(WeatherDaily(Day.Thursday, 20))
            add(WeatherDaily(Day.Fryday, 26))
            add(WeatherDaily(Day.Saturday, 30))
            add(WeatherDaily(Day.Sunday, 36))
            add(WeatherDaily(Day.Monday, -4))
            add(WeatherDaily(Day.Thuesday, -45))
            add(WeatherDaily(Day.Wednesday, 0))
            add(WeatherDaily(Day.Thursday, 20))
            add(WeatherDaily(Day.Fryday, 26))
            add(WeatherDaily(Day.Saturday, 30))
            add(WeatherDaily(Day.Sunday, 36))
            add(WeatherDaily(Day.Monday, -4))
            add(WeatherDaily(Day.Thuesday, -45))
            add(WeatherDaily(Day.Wednesday, 0))
            add(WeatherDaily(Day.Thursday, 20))
            add(WeatherDaily(Day.Fryday, 26))
            add(WeatherDaily(Day.Saturday, 30))
            add(WeatherDaily(Day.Sunday, 36))
            add(WeatherDaily(Day.Monday, -4))
            add(WeatherDaily(Day.Thuesday, -45))
            add(WeatherDaily(Day.Wednesday, 0))
            add(WeatherDaily(Day.Thursday, 20))
            add(WeatherDaily(Day.Fryday, 26))
            add(WeatherDaily(Day.Saturday, 30))
            add(WeatherDaily(Day.Sunday, 36))
            add(WeatherDaily(Day.Monday, -4))
            add(WeatherDaily(Day.Thuesday, -45))
            add(WeatherDaily(Day.Wednesday, 0))
            add(WeatherDaily(Day.Thursday, 20))
            add(WeatherDaily(Day.Fryday, 26))
            add(WeatherDaily(Day.Saturday, 30))
            add(WeatherDaily(Day.Sunday, 36))
            add(WeatherDaily(Day.Monday, -4))
            add(WeatherDaily(Day.Thuesday, -45))
            add(WeatherDaily(Day.Wednesday, 0))
            add(WeatherDaily(Day.Thursday, 20))
            add(WeatherDaily(Day.Fryday, 26))
            add(WeatherDaily(Day.Saturday, 30))
            add(WeatherDaily(Day.Sunday, 36))
            add(WeatherDaily(Day.Monday, -4))
            add(WeatherDaily(Day.Thuesday, -45))
            add(WeatherDaily(Day.Wednesday, 0))
            add(WeatherDaily(Day.Thursday, 20))
            add(WeatherDaily(Day.Fryday, 26))
            add(WeatherDaily(Day.Saturday, 30))
            add(WeatherDaily(Day.Sunday, 36))
        }
        recyclerView.adapter = WeatherAdapter(list)
    }
}