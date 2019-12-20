package com.amwa.weather.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amwa.weather.data.model.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(city: List<City>)

    @Query("SELECT * FROM City WHERE name LIKE '%' || :name || '%'")
    fun findBy(name: String): LiveData<List<City>>
}