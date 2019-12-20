package com.amwa.weather

import android.app.Application
import com.amwa.weather.data.CityRepository
import com.amwa.weather.data.ForecastRepository

class WeatherApp : Application() {

    fun getCityRepository() = CityRepository(this)
    fun getForecastRepository() = ForecastRepository()
}