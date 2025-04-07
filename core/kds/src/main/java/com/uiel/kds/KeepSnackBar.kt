package com.uiel.kds

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun KeepSnackBar(
    modifier: Modifier = Modifier,
    snackbarData: SnackbarData,
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp),
            text = snackbarData.visuals.message,
            fontWeight = FontWeight.Bold,
        )
    }
}