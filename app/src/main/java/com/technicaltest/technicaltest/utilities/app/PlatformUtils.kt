package com.technicaltest.technicaltest.utilities.app

import android.os.Build


object PlatformUtils {

    @JvmStatic
    fun getAndroidAPI(): String{
        return Build.VERSION.SDK_INT.toString()
    }

    @JvmStatic
    fun isGreaterOrEqualThanMarshmallow(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    @JvmStatic
    fun isGreaterOrEqualThanNougat(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    @JvmStatic
    fun isEqualThanNougatMR1(): Boolean {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1
    }

    @JvmStatic
    fun isEqualThanNougatMR1AndXiaomi(): Boolean {
        return isEqualThanNougatMR1() && Build.MANUFACTURER.equals("Xiaomi", true)
    }

    @JvmStatic
    fun isGreaterOrEqualThanNougatMR1(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
    }

    @JvmStatic
    fun isEqualThanOreo(): Boolean {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.O
    }

    @JvmStatic
    fun isGreaterOrEqualThanOreo(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    @JvmStatic
    fun isEqualThanOreoM1(): Boolean {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1
    }

    @JvmStatic
    fun isGreaterOrEqualThanOreoMR1(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
    }

    @JvmStatic
    fun isGreaterOrEqualThanPie(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    }

    @JvmStatic
    fun isGreaterOrEqualThanAndroid10(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }
}