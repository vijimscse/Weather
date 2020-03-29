package com.khoslalabs.weather.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.khoslalabs.weather.R
import com.khoslalabs.weather.WeatherApplication
import com.khoslalabs.weather.di.component.DashboardComponent
import kotlinx.android.synthetic.main.fragment_weather_forecast.*
import javax.inject.Inject


class DashboardActivity : AppCompatActivity() {


    @Inject
    lateinit var mDashboardComponent: DashboardComponent

    @Inject
    lateinit var mDashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        mDashboardComponent = (applicationContext as WeatherApplication)
            .mAppComponent.dashboardComponent().create()
        mDashboardComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val bottomSheetBehavior = BottomSheetBehavior.from(forecast_bottomsheet);
        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.peekHeight = 300;
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_DRAGGING;

        mDashboardViewModel.getCurrentWeatherInfo()
        mDashboardViewModel.getForecastInfo()

        mDashboardViewModel.mCurrentWeatherInfo.observe(this, Observer {
            it?.let {
                Log.d("it", "it :: $it")
            }
        })

        mDashboardViewModel.mForecastInfo.observe(this, Observer {
            it?.let {
                Log.d("it", "it :: $it")
            }
        })
//        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
////                    mButton2.setText(R.string.button2_peek)
//                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
////                    mButton2.setText(R.string.button2_hide)
//                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
////                    mButton2.setText(R.string.button2)
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//
//            }
//        })
    }
}
