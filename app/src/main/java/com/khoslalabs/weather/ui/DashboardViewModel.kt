package com.khoslalabs.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khoslalabs.weather.data.WeatherRepository
import com.khoslalabs.weather.data.dto.CurrentWeatherInfo
import com.khoslalabs.weather.data.dto.ForecastInfo
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private var _mCurrentWeatherInfo = MutableLiveData<CurrentWeatherInfo>()
    val mCurrentWeatherInfo: LiveData<CurrentWeatherInfo> = _mCurrentWeatherInfo

    private val _mForecastInfo = MutableLiveData<ForecastInfo>()
    val mForecastInfo: LiveData<ForecastInfo> = _mForecastInfo

    fun getCurrentWeatherInfo() {
        val result = weatherRepository.getCurrentWeatherInfo()
        result.observeForever {
            _mCurrentWeatherInfo.value = it
        }
    }

    fun getForecastInfo() {
        val result = weatherRepository.getForecastInfo()
        result.observeForever{
            _mForecastInfo.value = it
        }
    }
}