package com.khoslalabs.weather.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toSimpleString(dateText: String) : String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        val parsedDate = sdf.parse(dateText);
        val formatter = SimpleDateFormat("EE hh:mm aa");

        return formatter.format(parsedDate)
    }
}