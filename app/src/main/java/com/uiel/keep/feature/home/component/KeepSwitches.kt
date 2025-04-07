package com.uiel.keep.feature.home.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun KeepSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: SwitchColors = KeepSwitchDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = Switch(
    checked = checked,
    onCheckedChange = onCheckedChange,
    modifier = modifier,
    thumbContent = thumbContent,
    enabled = enabled,
    colors = colors,
    interactionSource = interactionSource,
)

object KeepSwitchDefaults {
    @Composable
    fun colors(
        checkedThumbColor: Color = Color.White,
        checkedTrackColor: Color = Color(0xFFFFA926),
        checkedBorderColor: Color = Color.Transparent,
        checkedIconColor: Color = Color(0xFFFFA926),
        uncheckedThumbColor: Color = Color.White,
        uncheckedTrackColor: Color = Color.DarkGray,
        uncheckedBorderColor: Color = Color.Transparent,
        uncheckedIconColor: Color = Color.DarkGray,
    ): SwitchColors = SwitchDefaults.colors(
        checkedThumbColor = checkedThumbColor,
        checkedTrackColor = checkedTrackColor,
        checkedBorderColor = checkedBorderColor,
        checkedIconColor = checkedIconColor,
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedTrackColor = uncheckedTrackColor,
        uncheckedBorderColor = uncheckedBorderColor,
        uncheckedIconColor = uncheckedIconColor,
    )
}