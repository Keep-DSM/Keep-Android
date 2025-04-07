package com.uiel.keep.util

import com.uiel.keep.BuildConfig

fun isTestMode(): Boolean {
    return !(isProduction() && !isDebug())
}

fun isProduction(): Boolean {
    return BuildConfig.FLAVOR == "prod"
}

fun isDebug(): Boolean {
    return BuildConfig.DEBUG
}