package com.devanand.leonztechweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devanand.leonztechweatherapp.models.ForecastResponse
import com.devanand.leonztechweatherapp.models.WeatherResponce
import com.devanand.leonztechweatherapp.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<WeatherResponce>()
    val weather: LiveData<WeatherResponce> get() = _weather

    private val _forecast = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse> get() = _forecast

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherData(city, apiKey)
                _weather.postValue(response)
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            }
        }
    }

    fun fetchWeatherForecast(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val forecastResponse = repository.getWeatherForecast(city, apiKey)
                _forecast.postValue(forecastResponse)
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            }
        }
    }

}