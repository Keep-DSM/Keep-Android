package com.uiery.keep.util

import android.content.Context
import com.uiery.keep.R
import java.time.LocalTime

fun LocalTime.toTimeString(context: Context): String {
    val hour = this.hour
    val minute = this.minute
    val isAm = hour < 12
    val displayHour = when {
        hour == 0 -> 12
        hour > 12 -> hour - 12
        else -> hour
    }
    val amPm = if (isAm) context.getString(R.string.am) else context.getString(R.string.pm)
    return "$amPm $displayHour:${minute}"
}