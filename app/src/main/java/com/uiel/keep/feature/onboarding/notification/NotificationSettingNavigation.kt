package com.uiel.keep.feature.onboarding.notification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uiel.keep.feature.onboarding.Onboarding

fun NavController.navigateToNotificationSetting(
    navOptions: NavOptions? = null
) = navigate(
    route = Onboarding.Route.NotificationSetting,
    navOptions = navOptions,
)

fun NavGraphBuilder.notificationSettingScreen(
    onNavigateSelectApp: () -> Unit,
) {
    composable<Onboarding.Route.NotificationSetting> {
        NotificationSettingScreen(
            onNavigateSelectApp = onNavigateSelectApp,
        )
    }
}