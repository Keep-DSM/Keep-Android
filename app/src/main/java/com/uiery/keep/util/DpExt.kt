package com.uiery.keep.util

import android.content.Context
import androidx.compose.ui.unit.Dp
import kotlin.math.roundToInt

/**
 * dp 값을 px 값으로 변환합니다.
 **/
fun Dp.toPx(context: Context): Int =
    (this.value * context.resources.displayMetrics.density).roundToInt()