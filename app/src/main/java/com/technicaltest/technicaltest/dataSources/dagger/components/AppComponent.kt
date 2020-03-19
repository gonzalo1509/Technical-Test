package com.technicaltest.technicaltest.dataSources.dagger.components

import com.technicaltest.technicaltest.app.activities.MapActivity
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import com.technicaltest.technicaltest.app.customViews.map.CustomMarkerInfoWindowView
import com.technicaltest.technicaltest.app.viewModels.map.MapViewModel
import com.technicaltest.technicaltest.dataSources.dagger.modules.AppModule
import com.technicaltest.technicaltest.utilities.helpers.CustomAlertDialog
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun technicalTestApplication(): TechnicalTestApplication

    fun inject(application: TechnicalTestApplication)
    fun inject(mapActivity: MapActivity)
    fun inject(mapViewModel: MapViewModel)
    fun inject(customMarkerInfoWindowView: CustomMarkerInfoWindowView)
    fun inject(customAlertDialog: CustomAlertDialog)
}