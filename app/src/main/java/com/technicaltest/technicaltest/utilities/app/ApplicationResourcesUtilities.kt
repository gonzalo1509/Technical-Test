package com.technicaltest.technicaltest.utilities.app

import android.util.Log
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import javax.inject.Inject

class ApplicationResourcesUtilities @Inject constructor(private val technicalTestApplication: TechnicalTestApplication) {

    private val TAG: String = ApplicationResourcesUtilities::class.java.simpleName

    fun getResourceById(id: Int): String {
        Log.d(TAG, "init getResourceById")
        return technicalTestApplication.resources.getString(id)
    }
}