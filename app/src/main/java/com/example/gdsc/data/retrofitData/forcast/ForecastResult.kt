package com.example.gdsc.data.retrofitData.forcast

import com.google.gson.annotations.SerializedName

data class ForecastResult (
    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("cnt") var cnt: Int? = null,
    @SerializedName("list") var list: List<CustomList>? = null,
    @SerializedName("city") var city: City? = City(),
)