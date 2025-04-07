package com.uiel.keep.feature.menu

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object MenuRoute

fun NavController.navigateToMenu(
    navOptions: NavOptions? = null
) = navigate(route = MenuRoute, navOptions = navOptions)

fun NavGraphBuilder.menuScreen(
    onNavigateDevTool: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable<MenuRoute> {
        MenuScreen(
            onNavigateDevTool = onNavigateDevTool,
            onNavigateBack = onNavigateBack,
        )
    }
}