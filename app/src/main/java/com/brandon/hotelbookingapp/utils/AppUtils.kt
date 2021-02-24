package com.brandon.hotelbookingapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.brandon.hotelbookingapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppUtils {

    fun isWifiAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: false
        }

    fun calculateHotelReviewFace(rating: Int): Int {
        var result = 0

        when (rating) {
            in 0..19 -> result = R.drawable.terrible_bad_review_face_24
            in 20..39 -> result = R.drawable.bad_review_face_24
            in 40..59 -> result = R.drawable.average_review_face_24
            in 60..79 -> result = R.drawable.good_review_face_24
            in 80..100 -> result = R.drawable.excellent_review_face_24
        }

        return result
    }

    fun getCurrentDate(): String {
        return SimpleDateFormat("EEE, d MMM", Locale.getDefault()).format(Date())
    }
}
