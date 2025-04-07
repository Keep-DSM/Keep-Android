package com.uiel.keep.feature.home.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentDescription(
    modifier: Modifier = Modifier,
    isKeep: Boolean,
    startTime: Long,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        val description = if(isKeep) "지키자가 켜져 있는 동안 선택한 앱은 사용할 수 없어요" else "지키자를 켜서 우리를 유혹하는 앱 사용을 막으세요"
        if(isKeep) {
            TimerContent(startTime = startTime)
        } else {
            Text(
                text = "지키자가 꺼져 있어요",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red,
            )
        }
        Text(
            text = description,
            color = Color.Gray,
        )
    }
}