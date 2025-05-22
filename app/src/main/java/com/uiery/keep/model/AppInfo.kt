package com.uiery.keep.model

import android.graphics.drawable.Drawable

data class AppInfo(
    val isChecked: Boolean,
    val packageName: String,
    val appName: String,
    val appIcon: Drawable,
)