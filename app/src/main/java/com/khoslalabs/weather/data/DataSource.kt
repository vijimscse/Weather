package com.khoslalabs.weather.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.khoslalabs.weather.data.dto.CurrentWeatherInfo
import com.khoslalabs.weather.data.dto.ForecastInfo
import com.khoslalabs.weather.utils.FileReader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class DataSource @Inject constructor(private val mApiService: ApiService) {
    @Inject
    lateinit var mContext: Context

    //Current Temperature data
    fun getCurrentTemperature(latitude: Double, longtitude: Double, listener: ResponseListener<CurrentWeatherInfo>) {
        if (ApiService.OFFLINE) {
            val json = FileReader.readFromAssets(mContext, "mockdata/current_weather.json")
            val type: Type = object : TypeToken<CurrentWeatherInfo>() {}.type
            val response: CurrentWeatherInfo = Gson().fromJson(json, type)
            listener.onResponse(response)
        } else {
            mApiService.getCurrentWeatherInfo(
                latitude,
                longtitude,
                "metric",
                "f120edeb83b2c4e0ea804b6718ba0b19"
            ).enqueue(object : Callback<CurrentWeatherInfo> {
                override fun onResponse(
                    call: Call<CurrentWeatherInfo>,
                    response: Response<CurrentWeatherInfo>
                ) {
                    listener.onResponse(response.body())
                }

                override fun onFailure(call: Call<CurrentWeatherInfo>, t: Throwable) {
                    Log.d("weather response", "on failure")
                    listener.onError(t.message)
                }
            })
        }
    }

    //Get forecast
    fun getForecastData(listener: ResponseListener<ForecastInfo>) {
        if (ApiService.OFFLINE) {
            val json = FileReader.readFromAssets(mContext, "mockdata/forecast_info.json")
            val type: Type = object : TypeToken<ForecastInfo>() {}.type
            val response: ForecastInfo = Gson().fromJson(json, type)
            Log.d("weather response", ""+ response)
            listener.onResponse(response)
        } else {
            mApiService.getForecast(
                "Gobichettipalayam",
                "metric",
                "f120edeb83b2c4e0ea804b6718ba0b19"
            ).enqueue(object : Callback<ForecastInfo> {
                override fun onResponse(
                    call: Call<ForecastInfo>,
                    response: Response<ForecastInfo>
                ) {
                    Log.d("weather response", ""+ response)
                    listener.onResponse(response.body())
                }

                override fun onFailure(call: Call<ForecastInfo>, t: Throwable) {
                    //listener.onResponse()
                    Log.d("weather response", "on failure")
                    listener.onError(t.message)
                }
            })
        }
    }
}

