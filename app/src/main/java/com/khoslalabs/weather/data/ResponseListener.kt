package com.khoslalabs.weather.data

interface ResponseListener<T> {
    fun onResponse(appResponse: T?)
}