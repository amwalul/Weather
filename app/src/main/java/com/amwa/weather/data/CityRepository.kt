package com.amwa.weather.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.amwa.weather.data.local.db.CityDao
import com.amwa.weather.data.local.db.CityDatabase
import com.amwa.weather.data.model.City

class CityRepository(application: Application) {

    private val cityDao: CityDao

    init {
        val cityDatabase = CityDatabase.getInstance(application)
        cityDao = cityDatabase.cityDao()
    }

    fun findCity(name: String): LiveData<List<City>> {
        return cityDao.findBy(name)
    }
}