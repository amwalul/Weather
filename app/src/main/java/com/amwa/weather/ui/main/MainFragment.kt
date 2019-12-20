package com.amwa.weather.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.amwa.weather.R
import com.amwa.weather.data.model.Forecast
import com.amwa.weather.utils.Utils
import com.amwa.weather.utils.gone
import com.amwa.weather.utils.visible
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.view_weather_banner.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.forecastList.observe(this, Observer {
            it?.let {
                llContent.visible()

                val todayForecast = it.first()
                val temp = Utils.kelvinToCelcius(todayForecast.main.temp)
                val location = todayForecast.city.name
                tvDate.text = Utils.getForecastDate(todayForecast.dateTime)
                tvTempHeader.text = getString(R.string.celcius_format, temp)
                tvLocationHeader.text = location
                tvTemp.text = getString(R.string.celcius_format, temp)
                tvLocation.text = location
                tvDesc.text = todayForecast.weatherList.first().description

                var currentIndex = 0
                for ((index, forecast) in it.withIndex()) {
                    if (index == it.size - 5) {
                        currentIndex = index
                        break
                    }else if (Utils.isNowPassed(forecast.dateTime)) {
                        currentIndex = index - 1
                        break
                    }
                }

                tvTime1.text = Utils.getForecastTime(it[currentIndex].dateTime)
                tvTemp1.text = getString(R.string.celcius_format, Utils.kelvinToCelcius(it[currentIndex].main.temp))
                currentIndex++
                tvTime2.text = Utils.getForecastTime(it[currentIndex].dateTime)
                tvTemp2.text = getString(R.string.celcius_format, Utils.kelvinToCelcius(it[currentIndex].main.temp))
                currentIndex++
                tvTime3.text = Utils.getForecastTime(it[currentIndex].dateTime)
                tvTemp3.text = getString(R.string.celcius_format, Utils.kelvinToCelcius(it[currentIndex].main.temp))
                currentIndex++
                tvTime4.text = Utils.getForecastTime(it[currentIndex].dateTime)
                tvTemp4.text = getString(R.string.celcius_format, Utils.kelvinToCelcius(it[currentIndex].main.temp))
                currentIndex++
                tvTime5.text = Utils.getForecastTime(it[currentIndex].dateTime)
                tvTemp5.text = getString(R.string.celcius_format, Utils.kelvinToCelcius(it[currentIndex].main.temp))

                val dailyWeatherAdapter = DailyWeatherAdapter()
                rvWeatherDaily.adapter = dailyWeatherAdapter

                it.forEachIndexed { index, forecast ->
                    if (index == 0 || Utils.isNewDay(forecast.dateTime)) {
                        dailyWeatherAdapter.addForecast(forecast)
                    }
                }

            } ?: Toast.makeText(context, "Error while fetching data", Toast.LENGTH_LONG).show()
        })
    }
}
