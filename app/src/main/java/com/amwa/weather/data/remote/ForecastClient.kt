package com.amwa.weather.data.remote

import com.amwa.weather.utils.Constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastClient {

    companion object {
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            synchronized(ForecastClient::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

                return retrofit!!
            }
        }
    }
}