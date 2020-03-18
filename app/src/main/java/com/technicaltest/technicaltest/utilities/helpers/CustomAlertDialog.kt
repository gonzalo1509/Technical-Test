package com.technicaltest.technicaltest.utilities.helpers

import android.app.Activity
import android.graphics.Color
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.technicaltest.technicaltest.R
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication.Companion.technicalTestApplication
import com.technicaltest.technicaltest.utilities.appUtilities.ApplicationResourcesUtilities
import javax.inject.Inject

class CustomAlertDialog {

    init {
        technicalTestApplication.appComponent.inject(this)
    }

    private val TAG: String = CustomAlertDialog::class.java.simpleName
    private lateinit var sweetAlertDialog: SweetAlertDialog

    @Inject
    lateinit var applicationResourcesUtilities: ApplicationResourcesUtilities

    fun initLoading(activity: Activity){
        Log.v(TAG, "init initLoading")

        if(isInitialized())
            dismissLoading()

        initInstanceLoading(
            activity,
            SweetAlertDialog.PROGRESS_TYPE
        )

        sweetAlertDialog.progressHelper.setBarColor(
            Color.parseColor(applicationResourcesUtilities.getResourceById(R.color.colorGreen)))
        sweetAlertDialog.titleText = applicationResourcesUtilities.getResourceById(
            R.string.txt_loading)
        sweetAlertDialog.setCancelable(false)

        sweetAlertDialog.show()
    }

    fun initErrorAlert(activity: Activity, message: String){
        Log.v(TAG, "init initErrorAlert")

        if(isInitialized())
            dismissLoading()

        initInstanceLoading(
            activity,
            SweetAlertDialog.ERROR_TYPE
        )

        sweetAlertDialog.progressHelper.setBarColor(
            Color.parseColor(applicationResourcesUtilities.getResourceById(R.color.colorRed)))
        sweetAlertDialog.titleText = applicationResourcesUtilities.getResourceById(
            R.string.txt_error_title)
        sweetAlertDialog.contentText = message
        sweetAlertDialog.setCancelable(false)
        sweetAlertDialog.show()
    }

    fun dismissLoading(){
        Log.v(TAG, "init dismissLoading")

        if(sweetAlertDialog.isShowing){
            Log.d(TAG, "Loading is showing, go to dismiss...")

            sweetAlertDialog.dismissWithAnimation()
        }
    }

    private fun initInstanceLoading(activity: Activity, type: Int){
        Log.v(TAG, "init initInstanceLoading")

        sweetAlertDialog = SweetAlertDialog(
            activity, type)
    }

    private fun isInitialized(): Boolean{
        Log.v(TAG, "init isInitialized")

        if(::sweetAlertDialog.isInitialized){
            Log.d(TAG, "Loading is showing")
            return true
        }

        return false
    }
}