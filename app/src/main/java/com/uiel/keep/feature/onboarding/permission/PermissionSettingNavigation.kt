package com.uiel.keep.feature.onboarding.permission

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uiel.keep.feature.onboarding.Onboarding

fun NavController.navigateToPermissionSetting(
    navOptions: NavOptions? = null,
) = navigate(
    route = Onboarding.Route.PermissionSetting,
    navOptions = navOptions,
)

fun NavGraphBuilder.permissionSettingScreen(
    onNavigateNotificationSetting: () -> Unit,
) {
    composable<Onboarding.Route.PermissionSetting> {
        PermissionSettingScreen(
            onNavigateNotificationSetting = onNavigateNotificationSetting,
        )
    }
}