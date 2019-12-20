package com.amwa.weather.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amwa.weather.BuildConfig
import com.amwa.weather.data.model.City
import com.amwa.weather.data.model.Forecast
import com.amwa.weather.data.model.ForecastResponse
import com.amwa.weather.data.remote.ForecastClient
import com.amwa.weather.data.remote.ForecastService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastRepository {

    private val forecastService: ForecastService = ForecastClient.getClient().create(ForecastService::class.java)
    private val apiKey = BuildConfig.WEATHER_API_KEY

    fun getForecastList(city: City): LiveData<List<Forecast>> {
        val forecastList: MutableLiveData<List<Forecast>> = MutableLiveData()

        forecastService.getForecastData(city.id, apiKey).enqueue(object :
            Callback<ForecastResponse> {
            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                forecastList.postValue(null)
            }

            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                response.body()?.let {
                    it.forecastList.forEach { forecast ->
                        forecast.city = city
                    }
                    forecastList.postValue(it.forecastList)
                } ?: kotlin.run {
                    forecastList.postValue(null)
                }
            }
        })

        return forecastList
    }
}