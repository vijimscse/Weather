package com.khoslalabs.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.khoslalabs.weather.di.component.DashboardComponent
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {

    @Inject
    lateinit var mDashboardComponent: DashboardComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mDashboardComponent = (applicationContext as WeatherApplication)
            .mAppComponent.dashboardComponent().create()
        mDashboardComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}
