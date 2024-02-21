package com.example.gdsc.network.ktor

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    const val TAG = "API체크"

    val json = Json {
        encodeDefaults = true // 직렬화할 때 기본값을 포함할지 여부

        ignoreUnknownKeys = false
    }

    val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d(TAG, "api log: $message")
                }
            }
            level = LogLevel.ALL
        }
        install(HttpTimeout){
            requestTimeoutMillis = 15_000
            connectTimeoutMillis = 15_000
            socketTimeoutMillis = 15_000
        }
        /*defaultRequest {
            //headers.append("Accept", "application/json")
            //headers.append("Content-Type", "application/json")
            headers.append("appid", BuildConfig.weather_api_key)
        }*/
    }
}