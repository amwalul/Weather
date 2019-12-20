package com.amwa.weather.utils

import android.content.Context
import com.amwa.weather.data.model.City
import com.amwa.weather.utils.Constant.CITY_LIST_FILE_NAME
import com.amwa.weather.utils.Constant.DATE_FORMAT_FORECAST_DATE
import com.amwa.weather.utils.Constant.DATE_FORMAT_FORECAST_DAY_NAME
import com.amwa.weather.utils.Constant.DATE_FORMAT_FORECAST_RESPONSE
import com.amwa.weather.utils.Constant.DATE_FORMAT_FORECAST_TIME
import org.json.JSONArray
import java.math.RoundingMode
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Utils {
    companion object {
        fun loadCityListFromJson(context: Context): List<City> {
            val inputStream = context.assets.open(CITY_LIST_FILE_NAME)
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            val jsonString = String(buffer, StandardCharsets.UTF_8)
            val cityList: ArrayList<City> = ArrayList()
            val cityObjectList = JSONArray(jsonString)
            for (i in 0 until cityObjectList.length()) {
                val cityObject = cityObjectList.getJSONObject(i)
                val city = City(cityObject)
                cityList.add(city)
            }

            return cityList
        }

        fun getForecastDate(dateTime: String): String {
            val currentFormat = SimpleDateFormat(DATE_FORMAT_FORECAST_RESPONSE, Locale.getDefault())
            val currentDate = currentFormat.parse(dateTime)!!

            val newFormat = SimpleDateFormat(DATE_FORMAT_FORECAST_DATE, Locale.getDefault())

            return newFormat.format(currentDate)
        }

        fun getForecastTime(dateTime: String): String {
            val currentFormat = SimpleDateFormat(DATE_FORMAT_FORECAST_RESPONSE, Locale.getDefault())
            val currentDate = currentFormat.parse(dateTime)!!

            val newFormat = SimpleDateFormat(DATE_FORMAT_FORECAST_TIME, Locale.getDefault())

            return newFormat.format(currentDate)
        }

        fun kelvinToCelcius(kelvin: Double) = (kelvin - 273.15F).toInt()

        fun isNowPassed(dateTime: String): Boolean {
            val currentFormat = SimpleDateFormat(DATE_FORMAT_FORECAST_RESPONSE, Locale.getDefault())
            val currentDate = currentFormat.parse(dateTime)!!
            if (System.currentTimeMillis() < currentDate.time) {
                return true
            }
            return false
        }

        fun isNewDay(dateTime: String): Boolean {
            val currentFormat = SimpleDateFormat(DATE_FORMAT_FORECAST_RESPONSE, Locale.getDefault())
            val currentDateTime = currentFormat.parse(dateTime)!!
            val calendar = Calendar.getInstance()
            calendar.time = currentDateTime
            val fasf = calendar.get(Calendar.HOUR_OF_DAY)

            return calendar.get(Calendar.HOUR_OF_DAY) == 0
        }

        fun getForecastDay(dateTime: String): String {
            val currentFormat = SimpleDateFormat(DATE_FORMAT_FORECAST_RESPONSE, Locale.getDefault())
            val currentDateTime = currentFormat.parse(dateTime)!!

            val newFormat = SimpleDateFormat(DATE_FORMAT_FORECAST_DAY_NAME, Locale.getDefault())

            return newFormat.format(currentDateTime)
        }
    }
}