package com.uiel.keep

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BlockScreen(
    modifier: Modifier = Modifier,
    packageName: String,
    onClose: () -> Unit,
) {
    val context = LocalContext.current
    val packageManager = context.packageManager

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
        val appName = packageManager.getApplicationLabel(applicationInfo).toString()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .sizeIn(
                        minHeight = 100.dp,
                        minWidth = 100.dp,
                    )
                    .clip(
                        RoundedCornerShape(12.dp)
                    ),
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "지키자가 앱 사용을\n막고 있어요",
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "${appName}은(는) 제한되었기 때문에 사용할 수 없습니다.",
                textAlign = TextAlign.Center,
            )
        }
        Button(
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = onClose,
            colors = ButtonColors(
                containerColor = Color(0xFFFE9E0B),
                contentColor = Color.White,
                disabledContentColor = Color(0xFFFE9E0B),
                disabledContainerColor = Color.White,
            ),
            contentPadding = PaddingValues(horizontal = 68.dp, vertical = 16.dp),
        ) {
            Text(text = "닫기")
        }
    }
}

@Preview
@Composable
private fun BlockScreenPreview() {
    BlockScreen(
        packageName = "",
        onClose = { },
    )
}