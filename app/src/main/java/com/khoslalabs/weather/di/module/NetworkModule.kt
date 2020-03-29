package com.khoslalabs.weather.di.module

import com.khoslalabs.weather.data.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// @Module informs Dagger that this class is a Dagger Module
@Module
class NetworkModule {

    // @Provides tell Dagger how to create instances of the type that this function
    // returns (i.e. ApiService).
    // Function parameters are the dependencies of this type.
    @Provides
    @Singleton
    fun provideRetrofitService(): ApiService {
        // Whenever Dagger needs to provide an instance of type ApiService,
        // this code (the one inside the @Provides method) is run.

        return Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}