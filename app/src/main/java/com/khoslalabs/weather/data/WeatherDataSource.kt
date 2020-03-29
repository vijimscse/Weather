package com.khoslalabs.weather.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khoslalabs.weather.data.dto.CurrentWeatherInfo
import com.khoslalabs.weather.data.dto.ForecastInfo
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class WeatherDataSource @Inject constructor(private val mDataSource: DataSource) {

    //Get current weather info
    fun getCurrentTemperature(): LiveData<CurrentWeatherInfo> {
        var weatherInfo = MutableLiveData<CurrentWeatherInfo>()
        mDataSource.getCurrentTemperature(object : ResponseListener<CurrentWeatherInfo> {
            override fun onResponse(appResponse: CurrentWeatherInfo?) {
                weatherInfo.value = appResponse
            }
        })

        return weatherInfo
    }

    //Get current weather info
    fun getForecastInfo(): LiveData<ForecastInfo> {
        var forecastInfo = MutableLiveData<ForecastInfo>()
        mDataSource.getForecastData(object : ResponseListener<ForecastInfo> {
            override fun onResponse(appResponse: ForecastInfo?) {
                forecastInfo.value = appResponse
            }
        })

        return forecastInfo
    }
}

