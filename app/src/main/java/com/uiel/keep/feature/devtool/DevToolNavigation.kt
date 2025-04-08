package com.uiel.keep.feature.devtool

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object DevToolRoute

fun NavController.navigateToDevTool(
    navOptions: NavOptions? = null
) = navigate(route = DevToolRoute, navOptions = navOptions)

fun NavGraphBuilder.devToolScreen(
    onNavigateBack: () -> Unit,
) {
    composable<DevToolRoute> {
        DevToolScreen(
            onNavigateBack = onNavigateBack,
        )
    }
}