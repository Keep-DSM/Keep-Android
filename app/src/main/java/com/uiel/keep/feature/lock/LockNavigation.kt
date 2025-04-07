package com.uiel.keep.feature.lock

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import kotlinx.serialization.Serializable

@Serializable
data class LockRoute(val lockTime: String)

fun NavController.navigateToLock(
    lockTime: String,
    navOptions: NavOptions = navOptions {
        popUpTo(graph.id) {
            inclusive = true
        }
    },
) = navigate(
    route = LockRoute(lockTime = lockTime),
    navOptions = navOptions,
)

fun NavGraphBuilder.lockScreen(
    onNavigateHome: () -> Unit,
) {
    composable<LockRoute> {
        LockScreen(
            onNavigateHome = onNavigateHome
        )
    }
}