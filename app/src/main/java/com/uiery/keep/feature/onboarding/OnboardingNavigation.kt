package com.uiery.keep.feature.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.uiery.keep.feature.onboarding.intro.introScreen
import com.uiery.keep.feature.onboarding.notification.notificationSettingScreen
import com.uiery.keep.feature.onboarding.permission.permissionSettingScreen
import com.uiery.keep.feature.onboarding.select.selectApp
import kotlinx.serialization.Serializable

@Serializable
sealed interface Onboarding {
    @Serializable
    data object Route {

        @Serializable
        data object Intro : Onboarding

        @Serializable
        data object PermissionSetting : Onboarding

        @Serializable
        data object NotificationSetting : Onboarding

        @Serializable
        data object SelectedApp : Onboarding
    }
}

fun NavController.navigateToOnboarding(
    route: Onboarding = Onboarding.Route.Intro,
    navOptions: NavOptions = navOptions {
        popUpTo(graph.id) {
            inclusive = true
        }
    },
) = navigate(route = route, navOptions = navOptions)

fun NavGraphBuilder.onboarding(
    onNavigatePermissionSetting: () -> Unit,
    onNavigateNotificationSetting: () -> Unit,
    onNavigateSelectApp: () -> Unit,
    onNavigateHome: () -> Unit,
) {
    navigation<Onboarding.Route>(
        startDestination = Onboarding.Route.Intro
    ) {
        introScreen(onNavigatePermissionSetting = onNavigatePermissionSetting)
        permissionSettingScreen(onNavigateNotificationSetting = onNavigateNotificationSetting)
        notificationSettingScreen(onNavigateSelectApp = onNavigateSelectApp)
        selectApp(onNavigateHome = onNavigateHome)
    }
}