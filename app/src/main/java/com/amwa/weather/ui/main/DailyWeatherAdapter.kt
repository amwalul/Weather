package com.amwa.weather.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amwa.weather.R
import com.amwa.weather.data.model.Forecast
import com.amwa.weather.utils.Utils
import com.amwa.weather.utils.gone
import com.amwa.weather.utils.visible
import kotlinx.android.synthetic.main.item_weather_daily.view.*

class DailyWeatherAdapter(var forecastList: ArrayList<Forecast> = ArrayList()) : RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder>() {

    fun addForecast(forecast: Forecast) {
        forecastList.add(forecast)
        notifyItemInserted(forecastList.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_daily, parent, false)
        return DailyWeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val forecast = forecastList[position]

        holder.itemView.tvDay.text = Utils.getForecastDay(forecast.dateTime)
        holder.itemView.tvDate.text = Utils.getForecastDate(forecast.dateTime)
        holder.itemView.tvTemp.text = holder.itemView.context.getString(R.string.celcius_format, Utils.kelvinToCelcius(forecast.main.temp))
        holder.itemView.tvDesc.text = forecast.weatherList.first().description

    }

    inner class DailyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}