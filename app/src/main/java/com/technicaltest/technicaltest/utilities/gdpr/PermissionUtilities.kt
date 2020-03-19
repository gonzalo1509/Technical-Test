package com.technicaltest.technicaltest.utilities.gdpr

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.technicaltest.technicaltest.utilities.app.PlatformUtils

object PermissionUtilities {
    val TAG: String = PermissionUtilities::class.java.simpleName

    fun checkIfPermissionsAreGranted(context: Context): Boolean {
        var granted = true
        var i = 0
        while (i < getPermissions().size && granted) {
            granted = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                context,
                getPermissions()[i]
            )
            i++
        }
        return granted
    }


    fun requestPermissionsHandler(activity: Activity): Boolean {
        var a: Boolean = false
        Dexter.withActivity(activity)
            .withPermissions(
                getPermissions()
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        Log.v(TAG, "Permissions was granted by the user")
                        a = true
                    }

                    if (report.isAnyPermissionPermanentlyDenied) {
                        Log.v(TAG, "Permissions not granted by the user")
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            })
            .onSameThread()
            .check()

        return a
    }

    private fun getPermissions(): List<String> {
        val permissionsList: MutableList<String> = mutableListOf()
        permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION)

        if (PlatformUtils.isGreaterOrEqualThanAndroid10()) {
            permissionsList.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        } else {
            permissionsList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        return permissionsList
    }

}