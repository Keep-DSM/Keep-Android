package com.uiery.keep.feature.onboarding.notification

import android.Manifest
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.uiery.kds.KeepButton
import com.uiery.kds.theme.KeepTheme
import com.uiery.keep.R

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

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = KeepTheme.colors.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
        ) {
            Text(
                modifier = Modifier.padding(top = 36.dp),
                text = stringResource(id = R.string.notification_permission_request),
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
                fontSize = 22.sp,
                color = KeepTheme.colors.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.notification_habit_message),
                color = KeepTheme.colors.surfaceVariant,
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
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.allow_notification_permission),
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