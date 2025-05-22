package com.uiery.keep.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uiery.kds.theme.KeepTheme
import com.uiery.keep.R
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigateHome:() -> Unit,
    onNavigateOnboarding: () -> Unit,
    onNavigateLock: (lockTime: String) -> Unit,
) {

    viewModel.collectSideEffect { effect ->
        when(effect) {
            is SplashSideEffect.MoveToHome -> onNavigateHome()
            is SplashSideEffect.MoveToOnboarding -> onNavigateOnboarding()
            is SplashSideEffect.MoveToLock -> onNavigateLock(effect.lockTime)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = KeepTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            painter = painterResource(R.drawable.app_icon),
            contentDescription = null,
        )
    }
}