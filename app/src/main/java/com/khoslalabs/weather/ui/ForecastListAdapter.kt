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
import kotlinx.android.synthetic.main.list_item_weather_forecast.view.*
import javax.inject.Inject


class ForecastListAdapter @Inject constructor(val mContext: Context) : RecyclerView.Adapter<ForecastListAdapter.VehicleViewHolder>() {
    var mForecastDays = listOf<ForecastDay>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return mForecastDays.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {

         return VehicleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_weather_forecast, parent, false))
    }

    inner class VehicleViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {
        val climateIcon: ImageView = view.climateIcon
        val day: TextView = view.day
        val tempMin: TextView = view.tempMin
        val tempMax: TextView = view.tempMax
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {

            mForecastDays[position].let {
                //holder.climateIcon.set() = it.name
                holder.day.text = it.dtTxt
                holder.tempMin.text = it.main?.tempMin.toString()
                holder.tempMax.text = it.main?.tempMax.toString()

            }

    }
}

