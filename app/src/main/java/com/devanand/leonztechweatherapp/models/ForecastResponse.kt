package com.devanand.leonztechweatherapp.models

data class ForecastResponse(
    val list: List<ForecastItem>,
    val city: City
)
