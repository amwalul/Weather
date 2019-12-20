package com.amwa.weather.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.amwa.weather.WeatherApp
import com.amwa.weather.data.model.City
import com.amwa.weather.data.model.Forecast

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val cityRepository = getApplication<WeatherApp>().getCityRepository()
    private val forecastRepository = getApplication<WeatherApp>().getForecastRepository()

    val forecastList: MediatorLiveData<List<Forecast>> = MediatorLiveData()

    init {
        getForecastList()
    }

    fun getForecastList() {
        forecastList.addSource(forecastRepository.getForecastList(City(1642911, "Jakarta"))) {
            forecastList.postValue(it)
        }
    }
}