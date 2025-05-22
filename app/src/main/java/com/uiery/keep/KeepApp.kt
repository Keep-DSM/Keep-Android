package com.uiery.keep

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uiery.keep.feature.devtool.devToolScreen
import com.uiery.keep.feature.devtool.navigateToDevTool
import com.uiery.keep.feature.home.homeScreen
import com.uiery.keep.feature.home.navigateToHome
import com.uiery.keep.feature.lock.lockScreen
import com.uiery.keep.feature.lock.navigateToLock
import com.uiery.keep.feature.menu.menuScreen
import com.uiery.keep.feature.menu.navigateToMenu
import com.uiery.keep.feature.onboarding.navigateToOnboarding
import com.uiery.keep.feature.onboarding.notification.navigateToNotificationSetting
import com.uiery.keep.feature.onboarding.onboarding
import com.uiery.keep.feature.onboarding.permission.navigateToPermissionSetting
import com.uiery.keep.feature.onboarding.select.navigateToSelectApp
import com.uiery.keep.feature.routine.navigateToRoutine
import com.uiery.keep.feature.routine.routineScreen
import com.uiery.keep.feature.splash.SplashRoute
import com.uiery.keep.feature.splash.splashScreen

@Composable
internal fun KeepApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = SplashRoute,
    ) {
        splashScreen(
            onNavigateHome = navController::navigateToHome,
            onNavigateOnboarding = navController::navigateToOnboarding,
            onNavigateLock = navController::navigateToLock,
        )
        onboarding(
            onNavigatePermissionSetting = navController::navigateToPermissionSetting,
            onNavigateNotificationSetting = navController::navigateToNotificationSetting,
            onNavigateSelectApp = navController::navigateToSelectApp,
            onNavigateHome = navController::navigateToHome,
        )
        homeScreen(
            onNavigateMenu = navController::navigateToMenu,
            onNavigateLock = navController::navigateToLock,
        )
        menuScreen(
            onNavigateDevTool = navController::navigateToDevTool,
            onNavigateBack = navController::navigateUp,
            onNavigateRoutine = navController::navigateToRoutine,
        )
        lockScreen(onNavigateHome = navController::navigateToHome)
        devToolScreen(onNavigateBack = navController::navigateUp)
        routineScreen(onNavigateBack = navController::navigateUp)
    }
}