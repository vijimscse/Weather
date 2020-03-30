package com.khoslalabs.weather.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.khoslalabs.weather.R
import com.khoslalabs.weather.WeatherApplication
import com.khoslalabs.weather.di.component.DashboardComponent
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.fragment_weather_forecast.*
import javax.inject.Inject


class DashboardActivity : AppCompatActivity() {

    companion object {
        const val LOCATION_REQUEST_CODE = 101
    }

    @Inject
    lateinit var mDashboardComponent: DashboardComponent

    @Inject
    lateinit var mDashboardViewModel: DashboardViewModel

    @Inject
    lateinit var mAdapter: ForecastListAdapter

    // declare a global variable of FusedLocationProviderClient
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private var mCurrentLocationLatitude: Double? = null
    private var mCurrentLocationLongtitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mDashboardComponent = (applicationContext as WeatherApplication)
            .mAppComponent.dashboardComponent().create()
        mDashboardComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()
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


        mDashboardViewModel.mCurrentWeatherInfo.observe(this, Observer {
            it?.let {
                progress_bar.visibility = View.GONE

                temperature.text = "" + it.main?.temp?.toInt() + "˚"
                description.text = it.weather?.get(0)?.description

                if (it.weather?.get(0)?.main?.contains("clear", true)!!)
                    current_weather_container.setBackgroundResource(R.drawable.sunny_background)
                else current_weather_container.setBackgroundResource(R.drawable.cloudy_background)

                pressure.text = "" + it.main?.pressure
                humidity.text = "" + it.main?.humidity
            }
        })

        mDashboardViewModel.mForecastInfo.observe(this, Observer {
            it?.let {
                mAdapter.mForecastDays = it.list
                forecast_progress_bar.visibility = View.GONE
            }
        })
    }

    /**
     * call this method for receive location
     * get location and give callback when successfully retrieve
     * function itself check location permission before access related methods
     *
     */
    private fun getLastKnownLocation() {
        mFusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    // use your location object
                    // get latitude , longitude and other info from this
                    mCurrentLocationLatitude = location.latitude
                    mCurrentLocationLongtitude = location.longitude

                    mDashboardViewModel.getCurrentWeatherInfo(mCurrentLocationLatitude!!,
                        mCurrentLocationLongtitude!!
                    )
                    mDashboardViewModel.getForecastInfo()
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] === PackageManager.PERMISSION_GRANTED
                ) {
                    getLastKnownLocation()
                }
            }
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            makeLocationPermissionRequest()
        } else {
            getLastKnownLocation()
        }
    }

    private fun makeLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), LOCATION_REQUEST_CODE
        )
    }
}
