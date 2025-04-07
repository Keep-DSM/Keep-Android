package com.uiel.keep.feature.onboarding.select

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.uiel.kds.KeepButton
import com.uiel.keep.R
import com.uiel.keep.feature.home.component.CategoryBottomSheetContent
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAppScreen(
    modifier: Modifier = Modifier,
    viewModel: SelectAppViewModel = hiltViewModel(),
    onNavigateHome: () -> Unit,
) {
    val uiState by viewModel.collectAsState()
    val categoryBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.picker_guide_lottie)
    )
    val coroutineScope = rememberCoroutineScope()

    if(uiState.isShowCategoryBottomSheet) {
        ModalBottomSheet(
            sheetState = categoryBottomSheetState,
            onDismissRequest = viewModel::hideCategoryBottomSheet,
        ) {
            CategoryBottomSheetContent(
                storeSelectApps = emptySet(),
                onComplete = { selectPackages ->
                    viewModel.selectCategoryComplete(selectPackages)
                    coroutineScope.launch {
                        categoryBottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!categoryBottomSheetState.isVisible) {
                            viewModel.hideCategoryBottomSheet()
                            onNavigateHome()
                        }
                    }
                },
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
        ) {
            Text(
                modifier = Modifier.padding(top = 36.dp),
                text = "이제 당신을 유혹하는 앱을\n선택해주세요",
                fontWeight = FontWeight.SemiBold,
                lineHeight = 28.sp,
                fontSize = 22.sp,
            )
            Text(
                modifier = Modifier.padding(top = 14.dp),
                text = "선택한 앱은 더 이상 사용되지 못하도록\n지키자가 앱 사용을 잠가드려요.\n앱 잠금은 언제든지 끄고 켤 수 있어요.",
                color = Color.Gray,
            )
            LottieAnimation(
                modifier = modifier.weight(1f),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
            KeepButton(
                text = "앱 선택하기",
                onClick = viewModel::showCategoryBottomSheet,
            )
        }
    }
}