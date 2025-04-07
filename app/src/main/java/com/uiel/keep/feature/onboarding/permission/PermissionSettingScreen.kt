package com.uiel.keep.feature.onboarding.permission

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiel.kds.KeepButton
import com.uiel.keep.R

@Composable
fun PermissionSettingScreen(
    modifier: Modifier = Modifier,
    onNavigateNotificationSetting: () -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 36.dp),
                text = "접근성 권한이 꼭 필요해요!",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "접근성 권한으로 우리를 유혹하는\n모든 앱 사용을 막을 수 있어요."
            )
            Row(
                modifier = Modifier.padding(top = 36.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.shield),
                    contentDescription = null,
                )
                Text(
                    text = "권한은 앱 사용을 막을 때만 사용"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            KeepButton(
                text = "권한 허용하기",
                onClick = {
                    if(hasAccessibilityPermission(context)) {
                        onNavigateNotificationSetting()
                    } else {
                        requestAccessibilityPermission(context)
                    }
                },
            )
        }
    }
}

fun hasAccessibilityPermission(context: Context): Boolean {
    val enabledServices = Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
    )
    return enabledServices != null && enabledServices.contains(context.packageName)
}

fun requestAccessibilityPermission(context: Context) {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}