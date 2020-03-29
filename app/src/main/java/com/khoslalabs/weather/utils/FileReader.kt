package com.khoslalabs.weather.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

object FileReader {

    fun readFromAssets(context: Context, fileName: String): String {
        var json: String?
        json = try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            ""
        }

        return json
    }
}