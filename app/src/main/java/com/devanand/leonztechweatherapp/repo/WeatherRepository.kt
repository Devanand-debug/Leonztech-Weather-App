package com.devanand.leonztechweatherapp.repo

import com.devanand.leonztechweatherapp.api.WeatherService
import com.devanand.leonztechweatherapp.models.ForecastResponse
import com.devanand.leonztechweatherapp.models.WeatherResponce
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {

    suspend fun getWeatherData(city : String, apiKey : String) : WeatherResponce{
        return weatherService.getCurrentWeather(city, apiKey)
    }

    //Fetch weather forcast - 5 days
    suspend fun getWeatherForecast(city: String, apiKey: String): ForecastResponse {
        return weatherService.getWeatherForecast(city, apiKey)
    }
}