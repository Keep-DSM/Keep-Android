package com.uiery.keep.feature.routine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uiery.kds.KeepModalBottomSheet
import com.uiery.kds.theme.KeepTheme
import com.uiery.keep.R
import com.uiery.keep.feature.routine.component.RoutineBottomSheetContent
import com.uiery.keep.feature.routine.component.RoutineNoContent
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineScreen(
    modifier: Modifier = Modifier,
    viewModel: RoutineViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    val state by viewModel.collectAsState()
    val routineBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    if(state.isShowRoutineBottomSheet) {
        KeepModalBottomSheet(
            sheetState = routineBottomSheetState,
            onDismissRequest = viewModel::hideRoutineBottomSheet,
        ) {
            RoutineBottomSheetContent(
                name = state.name,
                startTime = state.startTime,
                endTime = state.endTime,
                selectDays = state.selectDays,
                isPushEnabled = state.isPushEnabled,
                setName = viewModel::setName,
                isButtonEnabled = state.isButtonEnable,
                setStartTime = viewModel::setStartTime,
                setEndTime = viewModel::setEndTime,
                setPushEnabled = viewModel::setPushEnabled,
                onSelectDay = viewModel::setSelectDays,
            )
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = KeepTheme.colors.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "내 루틴",
                        color = KeepTheme.colors.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = null,
                            tint = KeepTheme.colors.primary,
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = viewModel::showRoutineBottomSheet
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = null,
                            tint = KeepTheme.colors.primary,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = KeepTheme.colors.background,
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            RoutineNoContent(
                modifier = Modifier.fillMaxSize(),
                onAddRoutine = viewModel::showRoutineBottomSheet,
            )
        }
    }
}