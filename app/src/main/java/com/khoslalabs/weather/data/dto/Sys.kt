package com.khoslalabs.weather.data.dto

import com.google.gson.annotations.SerializedName

data class Sys(

	@field:SerializedName("pod")
	val pod: String? = null
)