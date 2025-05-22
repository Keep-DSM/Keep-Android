package com.uiery.keep.feature.routine.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.uiery.kds.theme.KeepTheme
import com.uiery.keep.feature.home.component.KeepSwitch

@Composable
fun RoutineNameContent(
    modifier: Modifier = Modifier,
    name: String,
    isPushEnabled: Boolean,
    setName: (String) -> Unit,
    setPushEnabled:(Boolean) -> Unit,
) {
    RoutineSettingCard(
        modifier = modifier.fillMaxWidth(),
        topContent = {
            TextField(
                value = name,
                onValueChange = setName,
                placeholder = {
                    Text(
                        text = "루틴 이름",
                        color = KeepTheme.colors.onTertiary,
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = KeepTheme.colors.onSurfaceVariant,
                )
            )
        },
        bottomContent = {
            Text(
                text = "루틴 시작 시 푸시 발송",
                color = KeepTheme.colors.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.weight(1f))
            KeepSwitch(
                checked = isPushEnabled,
                onCheckedChange = setPushEnabled,
            )
        },
    )
}