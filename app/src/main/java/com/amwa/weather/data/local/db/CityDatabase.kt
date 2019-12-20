package com.amwa.weather.data.local.db

import android.app.Application
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.amwa.weather.data.model.City
import com.amwa.weather.utils.Constant.DB_NAME
import com.amwa.weather.utils.Utils

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object {
        private val lock = Any()
        private var INSTANCE: CityDatabase? = null

        fun getInstance(application: Application): CityDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application, CityDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                INSTANCE?.let {
                                    prePopulate(it, Utils.loadCityListFromJson(application))
                                }
                            }
                        })
                        .build()
                }
                return INSTANCE!!
            }
        }

        fun prePopulate(database: CityDatabase, cityList: List<City>) {
            AsyncTask.execute {
                database.cityDao().insertAll(cityList)
            }
        }
    }
}