package com.technicaltest.technicaltest.app.application

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.technicaltest.technicaltest.dataSources.dagger.components.AppComponent
import com.technicaltest.technicaltest.dataSources.dagger.components.DaggerAppComponent
import com.technicaltest.technicaltest.dataSources.dagger.modules.AppModule

class TechnicalTestApplication: Application() {

    companion object{
        lateinit var technicalTestApplication: TechnicalTestApplication
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        technicalTestApplication = this

        Log.d(TAG, ">>> ON CREATE >>>")
        super.onCreate()

        createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}