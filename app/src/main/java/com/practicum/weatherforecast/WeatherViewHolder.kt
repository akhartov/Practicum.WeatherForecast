package com.practicum.weatherforecast

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val layout: LinearLayout = itemView.findViewById(R.id.layout)
    private val week_day: TextView = itemView.findViewById(R.id.week_day)
    private val temperature_icon: ImageView = itemView.findViewById(R.id.temperature_icon)
    private val temperature_value: TextView = itemView.findViewById(R.id.temperature_value)

    fun bind(weather: WeatherDaily) {
        week_day.text = weather.weekDay.text
        when (weather.isSunnyDay()) {
            true -> {
                temperature_icon.setImageResource(R.drawable.ic_sun)
            }

            else -> {
                temperature_icon.setImageResource(R.drawable.ic_cloud)
            }
        }
        temperature_value.text = weather.temperatureString()

        temperature_icon.setBackgroundColor(weather.temperatureColor())
    }

}