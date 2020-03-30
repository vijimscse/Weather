package com.khoslalabs.weather

import android.os.Build
import android.util.Log
import android.widget.Spinner
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import com.khoslalabs.weather.ui.DashboardActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DashboardActivityTest {

    companion object {
        const val TAG = "DashboardActivityTest"
    }
    @get:Rule
    var activityRule: ActivityTestRule<DashboardActivity> = ActivityTestRule(DashboardActivity::class.java)

    @get:Rule var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    fun grantPermissionsIfNecessary() {
        if (Build.VERSION.SDK_INT >= 23) {
            val allowPermissions = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(UiSelector().text("ALLOW"))
            if (allowPermissions.exists()) {
                try {
                    allowPermissions.click()
                } catch (e: UiObjectNotFoundException) {
                    Log.e(TAG, "No permission dialog found.")
                }
            }
        }
    }

    @Test
    fun checkIfRecyclerViewExist() {

//        Espresso.onView(ViewMatchers.withId(R.id.forecast_list))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.forecast_bottomsheet))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}