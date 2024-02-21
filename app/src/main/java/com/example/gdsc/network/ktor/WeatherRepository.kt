package com.example.gdsc.network.ktor

import com.example.gdsc.BuildConfig
import com.example.gdsc.data.ktorData.WeatherData
import io.ktor.client.call.body
import io.ktor.client.request.get

object WeatherRepository {
    private const val weatherUrl = "https://api.openweathermap.org/data/2.5/weather"

    suspend fun getWeatherData(): WeatherData {
        return KtorClient.httpClient.get(weatherUrl) {
            url {
                parameters.append("q", "London")
                parameters.append("appid", BuildConfig.weather_api_key)
                parameters.append("units", "metric")
            }
        }.body<WeatherData>()
    }
}