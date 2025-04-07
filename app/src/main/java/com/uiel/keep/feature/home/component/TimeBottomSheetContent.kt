package com.uiel.keep.feature.home.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiel.keep.Picker
import com.uiel.keep.rememberPickerState
import java.time.LocalTime

@Composable
fun TimeBottomSheetContent(
    modifier: Modifier = Modifier,
    blockTime: LocalTime,
    onChangeCountdownTime: (LocalTime) -> Unit,
    onChangeTimerTIme: (LocalTime) -> Unit,
    onLockClick: () -> Unit,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
                .background(shape = RoundedCornerShape(8.dp), color = Color(0xFF18171E)),
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "종료 시간",
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.weight(1f))
                if(LocalTime.now() > blockTime) {
                    Text(
                        text = "다음날 ",
                        color = Color(0xFFFE9E0B),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    text = "${blockTime.hour}시 ${blockTime.minute}분까지",
                    color = Color(0xFFFE9E0B),
                    fontWeight = FontWeight.SemiBold
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                thickness = 1.dp,
                color = Color.DarkGray,
            )
            SegmentedControl(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 68.dp),
                items = listOf("카운트다운", "타이머"),
                onItemSelection = { selectedIndex = it },
            )
            Crossfade(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                targetState = selectedIndex
            ) {
                when(it) {
                    0 -> CountDownPicker(onChangeCountdownTime = onChangeCountdownTime)
                    1 -> TimerPicker(onChangeTimerTime = onChangeTimerTIme)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "시작 버튼을 누르면 선택한 시간까지 앱이 잠겨요",
            color = Color.LightGray,
        )
        val hour = blockTime.minusHours(LocalTime.now().hour.toLong()).hour
        val minute = blockTime.minusMinutes(LocalTime.now().minute.toLong()).minute
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 24.dp),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            enabled = hour != 0 || minute != 0,
            colors = ButtonColors(
                containerColor = Color(0xFFFFA926),
                contentColor = Color.White,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.White,
            ),
            onClick = onLockClick,
        ) {
            Text(
                text = "${hour}시간 ${minute}분 동안 잠금 시간",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }
    }
}

@Preview
@Composable
private fun TimeBottomSheetContentPreview() {
    TimeBottomSheetContent(
        blockTime = LocalTime.now(),
        onChangeCountdownTime = {},
        onChangeTimerTIme = {},
        onLockClick = {},
    )
}