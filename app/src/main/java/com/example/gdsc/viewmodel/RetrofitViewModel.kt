package com.example.gdsc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdsc.data.retrofitData.WeatherResult
import com.example.gdsc.data.retrofitData.forcast.ForecastResult
import com.example.gdsc.network.retrofit.RetrofitClient
import kotlinx.coroutines.launch


class RetrofitViewModel: ViewModel() {
    var state by mutableStateOf(STATE.LOADING)
    var weatherResponse : WeatherResult by mutableStateOf(WeatherResult())
    var forecastResponse : ForecastResult by mutableStateOf(ForecastResult())
    var errorMessage: String by mutableStateOf("")

    fun getWeatherByLocation(position : String) {
        viewModelScope.launch {
            state = STATE.LOADING
            val apiService = RetrofitClient.getApiService()
            try {
                val apiResponse = apiService.getWeather(position)
                weatherResponse = apiResponse
                state = STATE.SUCCESS
            }catch (e: Exception) {
                errorMessage = e.message.toString()
                state = STATE.ERROR
            }
        }
    }

    fun getForcastByLocation(position: String) {
        viewModelScope.launch {
            state = STATE.LOADING
            val apiService = RetrofitClient.getApiService()
            try {
                val apiResponse = apiService.getForecast(position)
                forecastResponse = apiResponse
                state = STATE.SUCCESS
            }catch (e: Exception) {
                errorMessage = e.message.toString()
                state = STATE.ERROR
            }
        }
    }
}

enum class STATE {
    LOADING,
    SUCCESS,
    ERROR
}
