package com.khoslalabs.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.khoslalabs.weather.R
import com.khoslalabs.weather.WeatherApplication
import com.khoslalabs.weather.di.component.DashboardComponent
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.fragment_weather_forecast.*
import javax.inject.Inject


class DashboardActivity : AppCompatActivity() {


    @Inject
    lateinit var mDashboardComponent: DashboardComponent

    @Inject
    lateinit var mDashboardViewModel: DashboardViewModel


    @Inject
    lateinit var mAdapter: ForecastListAdapter

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

        forecast_list.adapter = mAdapter
        forecast_list.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            forecast_list.getContext(),
            (forecast_list.layoutManager as LinearLayoutManager).orientation
        )
        forecast_list.addItemDecoration(dividerItemDecoration)
        progress_bar.visibility = View.VISIBLE
        forecast_progress_bar.visibility = View.VISIBLE
        mDashboardViewModel.getCurrentWeatherInfo()
        mDashboardViewModel.getForecastInfo()

        mDashboardViewModel.mCurrentWeatherInfo.observe(this, Observer {
            it?.let {
                Log.d("it", "it :: $it")
                progress_bar.visibility = View.GONE

                temperature.text = "" + it.main?.temp?.toInt() + "˚"
                description.text = it.weather?.get(0)?.description

//                if (it.weather?.get(0)?.main?.contains("sun")!!) {
//                    current_weather_container.setBackgroundResource(R.drawable.sunny_bg)
//                } else {
                    current_weather_container.setBackgroundResource(R.drawable.cloudy_background)
          //      }

            }
        })

        mDashboardViewModel.mForecastInfo.observe(this, Observer {
            it?.let {
                Log.d("it", "it :: $it")
                mAdapter.mForecastDays = it.list
                forecast_progress_bar.visibility = View.GONE
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
