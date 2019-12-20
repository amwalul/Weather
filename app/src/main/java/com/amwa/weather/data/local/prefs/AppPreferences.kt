package com.amwa.weather.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.amwa.weather.utils.Constant.PREF_KEY_CURRENT_CITY_ID
import com.amwa.weather.utils.Constant.PREF_KEY_FIRST_RUN

class AppPreferences(context: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setFirstRun(input: Boolean) = prefs.edit().putBoolean(PREF_KEY_FIRST_RUN, input).apply()

    fun getFirstRun() = prefs.getBoolean(PREF_KEY_FIRST_RUN, true)

    fun setCurrentCityId(id: Int) = prefs.edit().putInt(PREF_KEY_CURRENT_CITY_ID, id).apply()

    fun getCurrentCityId() = prefs.getInt(PREF_KEY_CURRENT_CITY_ID, 1215501)
}