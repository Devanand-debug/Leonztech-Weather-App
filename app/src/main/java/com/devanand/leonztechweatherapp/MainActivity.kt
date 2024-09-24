package com.devanand.leonztechweatherapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.devanand.leonztechweatherapp.databinding.ActivityMainBinding
import com.devanand.leonztechweatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiKey = getString(R.string.api_key)

        val textCityName = binding.etCity.text

        binding.btnWeather.setOnClickListener {
            weatherViewModel.fetchWeather("$textCityName", apiKey)
            weatherViewModel.fetchWeatherForecast("$textCityName", apiKey)
        }

        weatherViewModel.weather.observe(this, Observer { weather ->
            binding.tvTemperature.text = "Temperature - ${weather.main.temp}°C"
        })

        weatherViewModel.forecast.observe(this, Observer { forecast ->
            val forecastText = forecast.list.joinToString("\n") {
                "${it.dt_txt}: ${it.main.temp}°C, ${it.weather[0].description}"
            }
            binding.tvForcast.text = "Forecast - " + forecastText
        })

        weatherViewModel.error.observe(this, Observer { error ->
            Toast.makeText(this, "Please enter city name", Toast.LENGTH_SHORT).show()
        })
    }
}