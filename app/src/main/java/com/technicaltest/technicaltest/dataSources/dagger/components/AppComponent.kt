package com.technicaltest.technicaltest.dataSources.dagger.components

import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import com.technicaltest.technicaltest.app.viewModels.map.MapViewModel
import com.technicaltest.technicaltest.dataSources.dagger.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun technicalTestApplication(): TechnicalTestApplication

    fun inject(application: TechnicalTestApplication)
    fun inject(mapViewModel: MapViewModel)
}