package com.uiel.keep.feature.onboarding.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.uiel.kds.KeepButton
import com.uiel.keep.R

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    onNavigatePermissionSetting: () -> Unit,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.intro_lottie)
    )

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 48.dp),
                text = stringResource(R.string.onboarding_intro_title),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )
            LottieAnimation(
                modifier = Modifier.fillMaxSize().weight(1f),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
            KeepButton(
                text = stringResource(R.string.start),
                onClick = onNavigatePermissionSetting,
            )
        }
    }
}