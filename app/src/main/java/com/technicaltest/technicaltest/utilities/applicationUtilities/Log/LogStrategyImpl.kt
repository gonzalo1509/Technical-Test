package com.orange.agentsdk.Utilities.ApplicationUtilities.Log

import android.util.Log
import org.slf4j.LoggerFactory

class LogStrategyImpl(var minimumLogLevel: Int) : LogStrategy {

    override fun v(tag: String, message: String) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.VERBOSE >= minimumLogLevel) {
            logger.trace(message)
        }
    }

    override fun d(tag: String, message: String) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.DEBUG >= minimumLogLevel) {
            logger.debug(message)
        }
    }

    override fun i(tag: String, message: String) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.INFO >= minimumLogLevel) {
            logger.info(message)
        }
    }

    override fun w(tag: String, message: String) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.WARN >= minimumLogLevel) {
            logger.warn(message)
        }
    }

    override fun e(tag: String, message: String) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.ERROR >= minimumLogLevel) {
            logger.error(message)
        }
    }

    override fun v(tag: String, message: String, throwable: Throwable) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.VERBOSE >= minimumLogLevel) {
            logger.trace(message, throwable)
        }
    }

    override fun d(tag: String, message: String, throwable: Throwable) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.DEBUG >= minimumLogLevel) {
            logger.debug(message, throwable)
        }
    }

    override fun i(tag: String, message: String, throwable: Throwable) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.INFO >= minimumLogLevel) {
            logger.info(message, throwable)
        }
    }

    override fun w(tag: String, message: String, throwable: Throwable) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.WARN >= minimumLogLevel) {
            logger.warn(message, throwable)
        }
    }

    override fun e(tag: String, message: String, throwable: Throwable) {
        val logger = LoggerFactory.getLogger(tag)
        if (Log.ERROR >= minimumLogLevel) {
            logger.error(message, throwable)
        }
    }
    
    override fun isDebugEnabled(): Boolean {
        return getLoggingLevel()<= Log.DEBUG;
    }

    override fun getLoggingLevel(): Int {
        return minimumLogLevel
    }

    override fun setLoggingLevel(level: Int) {
        minimumLogLevel = level
    }

    override fun logLevelToString(loglevel: Int): String {
        return when (loglevel) {
            Log.VERBOSE -> "VERBOSE"
            Log.DEBUG -> "DEBUG"
            Log.INFO -> "INFO"
            Log.WARN -> "WARN"
            Log.ERROR -> "ERROR"
            Log.ASSERT -> "ASSERT"
            else -> "UNKNOWN"
        }
    }
}