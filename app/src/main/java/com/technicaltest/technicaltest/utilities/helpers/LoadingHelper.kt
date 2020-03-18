package com.technicaltest.technicaltest.utilities.helpers

import android.app.Activity
import android.graphics.Color
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog

class LoadingHelper {
    private val TAG: String = LoadingHelper::class.java.simpleName
    private lateinit var sweetAlertDialog: SweetAlertDialog

    fun initLoading(activity: Activity){
        Log.v(TAG, "init initLoading")

        if(isInitialized())
            return

        initInstanceLoading(
            activity,
            SweetAlertDialog.PROGRESS_TYPE
        )

        sweetAlertDialog.progressHelper.setBarColor(Color.parseColor("#A5DC86"))
        sweetAlertDialog.titleText = "Cargando..."
        sweetAlertDialog.setCancelable(false)

        sweetAlertDialog.show()
    }

    fun initErrorAlert(activity: Activity, message: String){
        Log.v(TAG, "init initErrorAlert")

        if(isInitialized())
            return

        initInstanceLoading(
            activity,
            SweetAlertDialog.ERROR_TYPE
        )

        sweetAlertDialog.progressHelper.setBarColor(Color.parseColor("#FE2E2E"))
        sweetAlertDialog.titleText = "Ups..."
        sweetAlertDialog.contentText = message
        sweetAlertDialog.setCancelable(false)
        sweetAlertDialog.show()
    }

    fun dismissLoading(){
        Log.v(TAG, "init dismissLoading")

        if(sweetAlertDialog.isShowing){
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
            if(sweetAlertDialog.isShowing){
                Log.d(TAG, "Loading is showing, abort init...")
                return true
            }
        }

        return false
    }
}