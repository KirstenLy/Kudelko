package com.example.tinkoff_lab.other

import com.example.tinkoff_lab.R
import com.example.tinkoff_lab.application.di.ApplicationScope
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/** Simple exception handler */
@ApplicationScope
class ExceptionHandler @Inject constructor(private val resourceProvider: ResourceProvider) {

    fun getExceptionString(e: Throwable): String {
        if (e is UnknownHostException) {
            return resourceProvider.getString(R.string.error_no_internet)
        }
        if (e is SocketTimeoutException) {
            return resourceProvider.getString(R.string.error_timeout)
        }
        return resourceProvider.getString(R.string.error_unknown)
    }
}