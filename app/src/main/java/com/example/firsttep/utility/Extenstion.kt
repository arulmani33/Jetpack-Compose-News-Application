package com.example.firsttep.utility

import com.google.gson.Gson
import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun <T> T.toJsonString(): String = Gson().toJson(this)

fun String.dateToTimeAgo(): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
    var ago: CharSequence? = null
    try {
        val time: Long = sdf.parse("2016-01-24T16:00:00.000Z").getTime()
        val now = System.currentTimeMillis()
        ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS) as String?
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ago.toString() ?: null
}