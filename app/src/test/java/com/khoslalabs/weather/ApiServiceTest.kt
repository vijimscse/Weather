package com.khoslalabs.weather

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.khoslalabs.weather.data.ApiService
import com.khoslalabs.weather.data.dto.CurrentWeatherInfo
import okhttp3.Request
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {
    @Mock
    lateinit var apiService: ApiService

    @Test
    fun testSomething() {
        val jsonString = "{\n" +
                "\"coord\": {\n" +
                "\"lon\": 77.44,\n" +
                "\"lat\": 11.45\n" +
                "},\n" +
                "\"weather\": [\n" +
                "{\n" +
                "\"id\": 803,\n" +
                "\"main\": \"Clouds\",\n" +
                "\"description\": \"broken clouds\",\n" +
                "\"icon\": \"04d\"\n" +
                "}\n" +
                "],\n" +
                "\"base\": \"stations\",\n" +
                "\"main\": {\n" +
                "\"temp\": 36,\n" +
                "\"feels_like\": 35.07,\n" +
                "\"temp_min\": 36,\n" +
                "\"temp_max\": 36,\n" +
                "\"pressure\": 1011,\n" +
                "\"humidity\": 34\n" +
                "},\n" +
                "\"visibility\": 7000,\n" +
                "\"wind\": {\n" +
                "\"speed\": 5.1,\n" +
                "\"deg\": 90\n" +
                "},\n" +
                "\"clouds\": {\n" +
                "\"all\": 74\n" +
                "},\n" +
                "\"dt\": 1585558423,\n" +
                "\"sys\": {\n" +
                "\"type\": 1,\n" +
                "\"id\": 9206,\n" +
                "\"country\": \"IN\",\n" +
                "\"sunrise\": 1585529279,\n" +
                "\"sunset\": 1585573263\n" +
                "},\n" +
                "\"timezone\": 19800,\n" +
                "\"id\": 1270947,\n" +
                "\"name\": \"Gobichettipalayam\",\n" +
                "\"cod\": 200\n" +
                "}"
        val type: Type = object : TypeToken<CurrentWeatherInfo>() {}.type
        val responseObj: CurrentWeatherInfo = Gson().fromJson(jsonString, type)

        Mockito.`when`(apiService.getCurrentWeatherInfo(11.4568, 77.4365, "metric","f120edeb83b2c4e0ea804b6718ba0b19"))
            .thenReturn(CallFake.buildSuccess(responseObj))

        val call = apiService.getCurrentWeatherInfo(11.4568, 77.4365, "metric","f120edeb83b2c4e0ea804b6718ba0b19")
        val response = call.execute()
        val userResponse = response.body() as CurrentWeatherInfo

        Assert.assertEquals(responseObj, userResponse)
    }
}