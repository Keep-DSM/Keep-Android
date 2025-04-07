package com.uiel.keep.feature.home.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.uiel.keep.R

@Composable
fun CategoryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean,
    categorySize: Int,
) {
    val moveIcon =
        if (enabled) R.drawable.round_arrow_forward_ios_24 else R.drawable.baseline_edit_off_24
    val textColor = animateColorAsState(
        targetValue = if (enabled) Color.White else Color.Gray,
        label = ""
    ).value
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick,
                enabled = enabled,
            )
            .background(shape = RoundedCornerShape(12.dp), color = Color.DarkGray)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Image(
            modifier = Modifier.size(28.dp),
            painter = painterResource(id = R.drawable.shield),
            contentDescription = null,
        )
        Text(
            text = "${categorySize}개의 앱&웹 카테고리 선택됨",
            fontWeight = FontWeight.SemiBold,
            color = textColor,
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = moveIcon),
            contentDescription = null,
            tint = Color.Gray,
        )
    }
}