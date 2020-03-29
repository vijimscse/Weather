package com.khoslalabs.weather.di.component

import com.khoslalabs.weather.ui.DashboardActivity
import com.khoslalabs.weather.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface DashboardComponent {
    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): DashboardComponent
    }

    fun inject(dashboardActivity: DashboardActivity)
}