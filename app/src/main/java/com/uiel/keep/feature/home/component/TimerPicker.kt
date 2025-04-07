package com.uiel.keep.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiel.keep.Picker
import com.uiel.keep.rememberPickerState
import java.time.LocalTime

@Composable
fun TimerPicker(
    modifier: Modifier = Modifier,
    onChangeTimerTime: (LocalTime) -> Unit,
) {
    val timePeriodsValues = remember { listOf("오전","오후") }
    val hourValues = remember { (0..11).map { it.toString() } }
    val minuteValues = remember { (0..59).map { it.toString() } }
    val timerPeriodsPickerState = rememberPickerState()
    val hourPickerState = rememberPickerState()
    val minutePickerState = rememberPickerState()

    LaunchedEffect(timerPeriodsPickerState.selectedItem,hourPickerState.selectedItem,minutePickerState.selectedItem) {
        if(hourPickerState.selectedItem.isNotEmpty() && minutePickerState.selectedItem.isNotEmpty()) {
            val hour = if(timerPeriodsPickerState.selectedItem == "오후") hourPickerState.selectedItem.toInt() + 12 else hourPickerState.selectedItem.toInt()
            onChangeTimerTime(LocalTime.of(hour,minutePickerState.selectedItem.toInt()))
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .height(32.dp)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF282831),
                )
        ) {

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Picker(
                state = timerPeriodsPickerState,
                items = timePeriodsValues,
                startIndex = if(LocalTime.now().isBefore(LocalTime.NOON)) 0 else 1,
                visibleItemsCount = 3,
                color = Color.White,
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                ),
                textModifier = Modifier.padding(vertical = 4.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Picker(
                modifier = Modifier.widthIn(min = 28.dp),
                state = hourPickerState,
                items = hourValues,
                startIndex = LocalTime.now().hour % 12,
                visibleItemsCount = 7,
                color = Color.White,
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                ),
                textModifier = Modifier.padding(vertical = 4.dp),
            )
            Spacer(modifier = Modifier.width(24.dp))
            Picker(
                modifier = Modifier.widthIn(min = 28.dp),
                state = minutePickerState,
                items = minuteValues,
                startIndex = LocalTime.now().minute,
                visibleItemsCount = 7,
                color = Color.White,
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                ),
                textModifier = Modifier.padding(vertical = 4.dp),
            )
        }
    }
}