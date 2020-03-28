package com.khoslalabs.weather.data

import com.khoslalabs.weather.data.model.AppResponse

interface ResponseListener<T> {
    fun onResponse(appResponse: AppResponse<T>?)
}