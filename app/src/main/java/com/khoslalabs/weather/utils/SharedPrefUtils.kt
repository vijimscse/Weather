package com.khoslalabs.weather.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

object SharedPrefUtils {

    private const val PRIVATE_MODE = 0
    private const val PREF_NAME = "weatherapp"

    private lateinit var mSharedPref: SharedPreferences
    private lateinit var mPrefsEditor: SharedPreferences.Editor

    fun init(context: Context) {
        mSharedPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        mPrefsEditor = mSharedPref.edit()
    }

    fun putString(key : String, value: String?) {
        mPrefsEditor.putString(key, value)
        mPrefsEditor.apply()
    }

    fun putBoolean(key : String, value: Boolean) {
        mPrefsEditor.putBoolean(key, value)
        mPrefsEditor.apply()
    }

    fun getString(key: String) = mSharedPref.getString(key, null)

    fun getBoolean(key: String) = mSharedPref.getBoolean(key, false)

}