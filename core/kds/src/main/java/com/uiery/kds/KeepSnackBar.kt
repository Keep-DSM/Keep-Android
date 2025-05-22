package com.uiery.kds

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.uiery.kds.theme.KeepTheme

@Composable
fun KeepSnackBar(
    modifier: Modifier = Modifier,
    snackbarData: SnackbarData,
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = KeepTheme.colors.onSecondary,
            contentColor = KeepTheme.colors.onSurfaceVariant,
        )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp),
            text = snackbarData.visuals.message,
            fontWeight = FontWeight.Bold,
        )
    }
}