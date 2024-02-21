package com.example.gdsc.network.retrofit

import com.example.gdsc.BuildConfig
import com.example.gdsc.data.retrofitData.WeatherResult
import com.example.gdsc.data.retrofitData.forcast.ForecastResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") q: String = "",
        @Query("appid") appid: String = BuildConfig.weather_api_key,
        @Query("units") units: String = "metric"
    ): WeatherResult

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") q: String = "",
        @Query("appid") appid: String = BuildConfig.weather_api_key,
        @Query("units") units: String = "metric"
    ): ForecastResult
}