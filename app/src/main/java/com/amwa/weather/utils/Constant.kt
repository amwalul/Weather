package com.amwa.weather.utils

object Constant {
    const val CITY_LIST_FILE_NAME = "city_list.json"
    const val DB_NAME = "City.db"
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    const val PREF_KEY_FIRST_RUN = "PREF_KEY_FIRST_RUN"
    const val PREF_KEY_CURRENT_CITY_ID = "PREF_KEY_CURRENT_CITY_ID"

    const val DATE_FORMAT_FORECAST_RESPONSE = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_FORECAST_DATE = "dd MMMM, yyyy"
    const val DATE_FORMAT_FORECAST_DAY_NAME = "EEEE"
    const val DATE_FORMAT_FORECAST_TIME = "HH:mm"
}