package com.uiel.keep.feature.devtool.component

import android.content.ClipData
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DevToolItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
) {
    val clipboardManager = LocalClipboardManager.current
    val onClipBoardClick = {
        val clipData = ClipData.newPlainText(title, content)
        val clipEntry = ClipEntry(clipData)
        clipboardManager.setClip(clipEntry)
    }

    Column(
        modifier = modifier.clickable { onClipBoardClick() }
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        Text(content)
    }
}