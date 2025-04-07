package com.uiel.keep.feature.onboarding.intro

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uiel.keep.feature.onboarding.Onboarding

fun NavController.navigateToIntro(
    navOptions: NavOptions? = null
) = navigate(
    route = Onboarding.Route.Intro,
    navOptions = navOptions
)

fun NavGraphBuilder.introScreen(
    onNavigatePermissionSetting: () -> Unit,
) {
    composable<Onboarding.Route.Intro> {
        IntroScreen(
            onNavigatePermissionSetting = onNavigatePermissionSetting,
        )
    }
}