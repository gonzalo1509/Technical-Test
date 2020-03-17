package com.orange.agentsdk.Utilities.ApplicationUtilities.Log

interface LogStrategy {

    fun v(tag: String, message: String)
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
    fun w(tag: String, message: String)
    fun e(tag: String, message: String)

    fun v(tag: String, message: String, throwable: Throwable)
    fun d(tag: String, message: String, throwable: Throwable)
    fun i(tag: String, message: String, throwable: Throwable)
    fun w(tag: String, message: String, throwable: Throwable)
    fun e(tag: String, message: String, throwable: Throwable)

    fun isDebugEnabled(): Boolean
    fun getLoggingLevel(): Int
    fun setLoggingLevel(level: Int)
    fun logLevelToString(loglevel: Int): String
}