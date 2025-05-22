package com.uiery.keep.feature.routine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uiery.kds.theme.KeepTheme

@Composable
fun RoutineSettingCard(
    modifier: Modifier = Modifier,
    topContent: @Composable (RowScope.() -> Unit),
    bottomContent: @Composable (RowScope.() -> Unit),
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = KeepTheme.colors.secondary,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 20.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = topContent,
        )
        HorizontalDivider(
            color = KeepTheme.colors.onTertiary,
        )
        Row(
            modifier = Modifier.padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = bottomContent,
        )
    }
}