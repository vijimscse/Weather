package com.khoslalabs.weather

import android.app.Application
import com.khoslalabs.weather.utils.SharedPrefUtils
import com.khoslalabs.weather.di.component.DashboardComponent
import com.khoslalabs.weather.di.module.AppModule
import com.khoslalabs.weather.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun dashboardComponent(): DashboardComponent.Factory
}

class WeatherApplication : Application() {

    // Reference to the application graph that is used across the whole app
    val mAppComponent: ApplicationComponent = DaggerApplicationComponent.builder().appModule(
        AppModule(this)
    ).build()

    override fun onCreate() {
        super.onCreate()

        SharedPrefUtils.init(this)
    }
}