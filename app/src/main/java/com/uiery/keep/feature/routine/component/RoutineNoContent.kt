package com.uiery.keep.feature.routine.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiery.kds.KeepButton
import com.uiery.kds.theme.KeepTheme

@Composable
fun RoutineNoContent(
    modifier: Modifier = Modifier,
    onAddRoutine: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "루틴을 추가하여 앱 잠금을 더 편리하게!\n지키자가 알아서 앱을 잠궈줘요.",
            color = KeepTheme.colors.onSurfaceVariant,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(20.dp))
        KeepButton(
            text = "루틴 추가하기",
            onClick = onAddRoutine,
        )
    }
}