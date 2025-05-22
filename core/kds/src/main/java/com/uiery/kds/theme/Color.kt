package com.uiery.kds.theme

import androidx.compose.ui.graphics.Color

internal sealed class KeepColor(
    val background: Color,
    val dimmedBackground: Color,
    val orange400: Color,
    val red500: Color,
    val gray50: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,
    val gray700: Color,
    val gray800: Color,
    val gray900: Color,
) {
    data object Light: KeepColor(
        background = Color(0xFFFFFFFF),
        dimmedBackground = Color(0xFF17171C),
        orange400 = Color(0xFFFFA927),
        red500 = Color(0xFFF04452),
        gray50 = Color(0xFFF9FAFB),
        gray100 = Color(0xFFF2F4F6),
        gray200 = Color(0xFFE5E8EB),
        gray300 = Color(0xFFD1D6DB),
        gray400 = Color(0xFFB0B8C1),
        gray500 = Color(0xFF8B95A1),
        gray600 = Color(0xFF6B7684),
        gray700 = Color(0xFF4E5968),
        gray800 = Color(0xFF333D4B),
        gray900 = Color(0xFF191F28),
    )
    data object Dark: KeepColor(
        background = Color(0xFF17171C),
        dimmedBackground = Color(0x8E000000),
        orange400 = Color(0xFFFFA927),
        red500 = Color(0xFFF04452),
        gray50 = Color(0xFF202027),
        gray100 = Color(0xFF2C2C35),
        gray200 = Color(0xFF3C3C47),
        gray300 = Color(0xFF4D4D59),
        gray400 = Color(0xFF62626D),
        gray500 = Color(0xFF7E7E87),
        gray600 = Color(0xFF9E9EA4),
        gray700 = Color(0xFFC3C3C6),
        gray800 = Color(0xFFE4E4E5),
        gray900 = Color(0xFFFFFFFF),
    )
}
