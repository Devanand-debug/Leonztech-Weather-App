package com.devanand.leonztechweatherapp.models

data class ForecastItem(
    val dt_txt: String, // Date and time
    val main: Main,
    val weather: List<Weather>
)
