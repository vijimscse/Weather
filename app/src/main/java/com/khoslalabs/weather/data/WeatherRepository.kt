package com.khoslalabs.weather.data

import androidx.lifecycle.LiveData
import com.khoslalabs.weather.data.dto.CurrentWeatherInfo
import com.khoslalabs.weather.data.dto.ForecastInfo
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
@Singleton
class WeatherRepository @Inject constructor(private val mWeatherDataSource: WeatherDataSource) {

    //Get current weather info
    fun getCurrentWeatherInfo(): LiveData<CurrentWeatherInfo> {
        return mWeatherDataSource.getCurrentTemperature()
    }

    //Get current weather info
    fun getForecastInfo(): LiveData<ForecastInfo> {
        return mWeatherDataSource.getForecastInfo()
    }
}