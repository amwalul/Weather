package com.amwa.weather.data.remote

import com.amwa.weather.data.model.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("forecast")
    fun getForecastData(@Query("id") cityId: Int, @Query("appid") apiKey: String): Call<ForecastResponse>
}