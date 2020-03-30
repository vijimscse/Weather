package com.khoslalabs.weather.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khoslalabs.weather.R
import com.khoslalabs.weather.data.dto.ForecastDay
import com.khoslalabs.weather.utils.DateUtils
import kotlinx.android.synthetic.main.list_item_weather_forecast.view.*
import javax.inject.Inject


class ForecastListAdapter @Inject constructor(val mContext: Context) :
    RecyclerView.Adapter<ForecastListAdapter.VehicleViewHolder>() {
    var mForecastDays = listOf<ForecastDay>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return mForecastDays.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {

        return VehicleViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_weather_forecast, parent, false)
        )
    }

    inner class VehicleViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {
        val climateIcon: ImageView = view.climateIcon
        val day: TextView = view.day
        val tempRange: TextView = view.tempRange
        val currentTemp: TextView = view.current_temp
        val description: TextView = view.description
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {

        mForecastDays[position].let { it ->
            //holder.climateIcon.set() = it.name

            // SimpleDateFormat.getDateInstance().parse(it.dtTxt)
            it.dtTxt?.let {
                holder.day.text = DateUtils.toSimpleString(it)
            }
            holder.tempRange.text =
                "Min " + it.main?.tempMin?.toInt() + "˚c to " + it.main?.tempMax?.toInt() + "˚c Max"

            holder.currentTemp.text = "" + it.main?.temp?.toInt() + "˚c"
            holder.description.text = it.weather?.get(0)?.description
            if (mForecastDays[position].weather?.get(0)?.main?.contains("cloud", true)!!) {
                holder.climateIcon.setImageResource(R.drawable.cloudy)
            } else if (mForecastDays[position].weather?.get(0)?.main?.contains("rain", true)!!) {
                holder.climateIcon.setImageResource(R.drawable.rainy)
            } else {
                holder.climateIcon.setImageResource(R.drawable.sunny)
            }
        }
    }
}

