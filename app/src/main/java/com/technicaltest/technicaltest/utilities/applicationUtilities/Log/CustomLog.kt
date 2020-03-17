package com.orange.agentsdk.Utilities.ApplicationUtilities.Log

import android.util.Log

object CustomLog {

    var logStrategy: LogStrategyImpl = LogStrategyImpl(Log.VERBOSE)

    fun v(tag: String, message: String) {
        return logStrategy.v(tag, message)
    }

    fun d(tag: String, message: String) {
        return logStrategy.d(tag, message)
    }

    fun i(tag: String, message: String) {
        return logStrategy.i(tag, message)
    }

    fun w(tag: String, message: String) {
        return logStrategy.w(tag, message)
    }

    fun e(tag: String, message: String) {
        return logStrategy.e(tag, message)
    }

    fun v(tag: String, message: String, throwable: Throwable) {
        return logStrategy.v(tag, message, throwable)
    }

    fun d(tag: String, message: String, throwable: Throwable) {
        return logStrategy.d(tag, message, throwable)
    }

    fun i(tag: String, message: String, throwable: Throwable) {
        return logStrategy.i(tag, message, throwable)
    }

    fun w(tag: String, message: String, throwable: Throwable) {
        return logStrategy.w(tag, message, throwable)
    }

    fun e(tag: String, message: String, throwable: Throwable) {
        return logStrategy.e(tag, message, throwable)
    }

    fun isDebugEnabled(): Boolean {
        return logStrategy.isDebugEnabled()
    }

    fun getLoggingLevel(): Int {
        return logStrategy.getLoggingLevel()
    }

    fun setLoggingLevel(level: Int) {
        logStrategy.setLoggingLevel(level)
    }

    fun logLevelToString(loglevel: Int): String {
        return logStrategy.logLevelToString(loglevel)
    }
}