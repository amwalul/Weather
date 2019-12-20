package com.amwa.weather.data.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("list")
    val forecastList: List<Forecast>,
    @SerializedName("city")
    val city: City
)

data class Forecast(
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weatherList: List<Weather>,
    @SerializedName("dt_txt")
    val dateTime: String,
    var city: City
)

data class Main(
    @SerializedName("temp")
    val temp: Double
)

data class Weather(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val group: String,
    @SerializedName("description")
    val description: String
)

