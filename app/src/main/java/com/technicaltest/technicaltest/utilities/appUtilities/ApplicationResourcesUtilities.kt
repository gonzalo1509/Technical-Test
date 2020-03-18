package com.technicaltest.technicaltest.utilities.appUtilities

import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import javax.inject.Inject

class ApplicationResourcesUtilities @Inject constructor(private val technicalTestApplication: TechnicalTestApplication){

    fun getResourceById(id: Int): String{
        return technicalTestApplication.resources.getString(id)
    }
}