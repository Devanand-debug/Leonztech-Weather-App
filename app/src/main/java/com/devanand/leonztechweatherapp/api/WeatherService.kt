package com.devanand.leonztechweatherapp.api

import com.devanand.leonztechweatherapp.models.ForecastResponse
import com.devanand.leonztechweatherapp.models.WeatherResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponce

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): ForecastResponse
}