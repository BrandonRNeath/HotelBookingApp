package com.brandon.hotelbookingapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


object AppUtils {
    fun isWifiAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: false
        }
}