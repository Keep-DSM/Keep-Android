package com.uiel.keep.feature.onboarding.notification

import android.Manifest
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.uiel.kds.KeepButton
import com.uiel.keep.R

private fun ManagedActivityResultLauncher<String, Boolean>.requestNotificationPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}

@Composable
fun NotificationSettingScreen(
    modifier: Modifier = Modifier,
    onNavigateSelectApp: () -> Unit,
) {
    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        onNavigateSelectApp()
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.notification_bell)
    )

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
        ) {
            Text(
                modifier = Modifier.padding(top = 36.dp),
                text = "지키자와 함께 약속을 잘 지킬 수 있도록 알림을 허용해주세요.",
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
                fontSize = 22.sp,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "스마트폰 사용 습관이 잘 만들어질 수 있도록\n지키자가 알려드릴게요"
            )
            LottieAnimation(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(horizontal = 100.dp),
                composition = composition,
                speed = 0.8f,
                iterations = LottieConstants.IterateForever,
            )
            KeepButton(
                text = "알림 권한 허용하기",
                onClick = {
                    requestPermissionLauncher.requestNotificationPermission()
                },
            )
        }
    }
}

@Preview
@Composable
private fun NotificationSettingScreenPreview() {
    NotificationSettingScreen(onNavigateSelectApp = {})
}