package com.uiel.keep

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uiel.keep.feature.devtool.devToolScreen
import com.uiel.keep.feature.devtool.navigateToDevTool
import com.uiel.keep.feature.home.HomeRoute
import com.uiel.keep.feature.home.homeScreen
import com.uiel.keep.feature.home.navigateToHome
import com.uiel.keep.feature.lock.lockScreen
import com.uiel.keep.feature.lock.navigateToLock
import com.uiel.keep.feature.menu.menuScreen
import com.uiel.keep.feature.menu.navigateToMenu
import com.uiel.keep.feature.onboarding.navigateToOnboarding
import com.uiel.keep.feature.onboarding.notification.navigateToNotificationSetting
import com.uiel.keep.feature.onboarding.onboarding
import com.uiel.keep.feature.onboarding.permission.navigateToPermissionSetting
import com.uiel.keep.feature.onboarding.select.navigateToSelectApp
import com.uiel.keep.feature.splash.SplashRoute
import com.uiel.keep.feature.splash.navigateToSplash
import com.uiel.keep.feature.splash.splashScreen

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
            onNavigateBack = navController::navigateUp
        )
        lockScreen(onNavigateHome = navController::navigateToHome)
        devToolScreen(onNavigateBack = navController::navigateUp)
    }
}