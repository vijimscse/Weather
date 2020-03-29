package com.khoslalabs.weather.data

import com.khoslalabs.weather.data.dto.CurrentWeatherInfo
import com.khoslalabs.weather.data.dto.ForecastInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val OFFLINE = true
    }

    @GET("weather")
    fun getCurrentWeatherInfo(
        @Query("q") cityName: String, @Query("units") units: String,
        @Query("appid") appId: String
    ): Call<CurrentWeatherInfo>

    @GET("forecast")
    fun getForecast(
        @Query("q") cityName: String, @Query("units") units: String,
        @Query("appid") appId: String,
        @Query("cnt") count: Int
    ): Call<ForecastInfo>
}