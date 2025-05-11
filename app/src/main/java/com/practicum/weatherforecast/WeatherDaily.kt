package com.practicum.weatherforecast

data class WeatherDaily(val weekDay: Day, val temperature: Int) {
    fun isSunnyDay(): Boolean {
        return temperature > 7
    }

    fun temperatureString(): String {
        return when {
            temperature > 0 -> "+${temperature} C"
            temperature < 0 -> " ${temperature} C"
            else -> "0 C"
        }
    }

    fun temperatureColor(): Int {
        return when {
            temperature < 0 -> R.color.blue
            temperature > 0 ->R.color.red
            else -> R.color.gray
        }
    }
}