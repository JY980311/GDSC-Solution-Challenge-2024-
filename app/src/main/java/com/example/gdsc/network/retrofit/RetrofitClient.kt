package com.example.gdsc.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private var apiService: IApiService? = null
        fun getApiService(): IApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IApiService::class.java)
            }
            return apiService!!
        }
    }
}