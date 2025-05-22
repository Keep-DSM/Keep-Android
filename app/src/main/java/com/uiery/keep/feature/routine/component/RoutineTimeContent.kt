package com.uiery.keep.feature.routine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.uiery.kds.theme.KeepTheme
import com.uiery.keep.feature.home.component.TimerPicker
import com.uiery.keep.util.toTimeString
import java.time.LocalTime

@Composable
fun RoutineTimeContent(
    modifier: Modifier = Modifier,
    startTime: LocalTime,
    endTime: LocalTime,
    setStartTime: (LocalTime) -> Unit,
    setEndTime: (LocalTime) -> Unit,
) {
    val context = LocalContext.current
    var isShowStartTimePicker by remember { mutableStateOf(false) }
    var isShowEndTimePicker by remember { mutableStateOf(false) }

    if (isShowStartTimePicker) {
        Dialog(
            onDismissRequest = { isShowStartTimePicker = false }
        ) {
            TimerPicker(
                modifier = Modifier
                    .background(
                        color = KeepTheme.colors.onSecondary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 40.dp),
                onChangeTimerTime = setStartTime,
                time = startTime,
            )
        }
    }

    if (isShowEndTimePicker) {
        Dialog(
            onDismissRequest = { isShowEndTimePicker = false }
        ) {
            TimerPicker(
                modifier = Modifier
                    .background(
                        color = KeepTheme.colors.onSecondary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 40.dp),
                onChangeTimerTime = setEndTime,
                time = endTime,
            )
        }
    }

    RoutineSettingCard(
        modifier = modifier.fillMaxWidth(),
        topContent = {
            Text(
                text = "시작 시간",
                color = KeepTheme.colors.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.weight(1f))
            RoutineTimeButton(
                text = startTime.toTimeString(context),
                onClick = { isShowStartTimePicker = true }
            )
        },
        bottomContent = {
            Text(
                text = "종료 시간",
                color = KeepTheme.colors.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.weight(1f))
            RoutineTimeButton(
                text = endTime.toTimeString(context),
                onClick = {  isShowEndTimePicker = true}
            )
        },
    )
}