package com.example.gdsc.data.retrofitData.forcast

import com.example.gdsc.data.retrofitData.Clouds
import com.example.gdsc.data.retrofitData.Main
import com.example.gdsc.data.retrofitData.Sys
import com.example.gdsc.data.retrofitData.Weather
import com.example.gdsc.data.retrofitData.Wind
import com.google.gson.annotations.SerializedName

data class CustomList (
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("main") var main: Main? = Main(),
    @SerializedName("weather") var weather: ArrayList<Weather>? = arrayListOf(),
    @SerializedName("clouds") var clouds: Clouds? = Clouds(),
    @SerializedName("wind") var wind: Wind? = Wind(),
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("pop") var pop: Double? = null,
    @SerializedName("sys") var sys: Sys? = Sys(),
    @SerializedName("dt_txt") var dt_txt: String? = null
    )