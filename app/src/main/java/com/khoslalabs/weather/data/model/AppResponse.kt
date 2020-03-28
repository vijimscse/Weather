package com.khoslalabs.weather.data.model

data class AppResponse<T> (
    var status: Int? = null,
    var error: String? = null,
    var result: T? = null
)