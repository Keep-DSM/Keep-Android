package com.uiery.keep.feature.routine.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uiery.kds.KeepButton
import com.uiery.kds.theme.KeepTheme
import com.uiery.keep.R
import com.uiery.keep.feature.home.component.CategoryBottomSheetContent
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun RoutineBottomSheetContent(
    modifier: Modifier = Modifier,
    name: String,
    startTime: LocalTime,
    endTime: LocalTime,
    selectDays: List<DayOfWeek>,
    isButtonEnabled: Boolean,
    isPushEnabled: Boolean,
    setName: (String) -> Unit,
    setStartTime: (LocalTime) -> Unit,
    setEndTime: (LocalTime) -> Unit,
    setPushEnabled: (Boolean) -> Unit,
    onSelectDay: (DayOfWeek) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        2
    })
    val coroutineScope = rememberCoroutineScope()
    val moveAppSelect: () -> Unit = {
        coroutineScope.launch {
            pagerState.animateScrollToPage(
                page = 1,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        }
    }
    val moveRoutineSetting: () -> Unit = {
        coroutineScope.launch {
            pagerState.animateScrollToPage(
                page = 0,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        }
    }
    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState,
        userScrollEnabled = false,
    ) { page ->
        when(page) {
            0 -> RoutineInputContent(
                name = name,
                startTime = startTime,
                endTime = endTime,
                selectDays = selectDays,
                isButtonEnabled = isButtonEnabled,
                isPushEnabled = isPushEnabled,
                onAppSelect = moveAppSelect,
                setName = setName,
                setStartTime = setStartTime,
                setEndTime = setEndTime,
                setPushEnabled = setPushEnabled,
                onSelectDay = onSelectDay,
            )
            1 -> {
                RoutineAppSelectionContent(
                    onBackClick = moveRoutineSetting,
                )
            }
        }
    }
}

@Composable
private fun RoutineInputContent(
    modifier: Modifier = Modifier,
    name: String,
    startTime: LocalTime,
    endTime: LocalTime,
    selectDays: List<DayOfWeek>,
    isButtonEnabled: Boolean,
    isPushEnabled: Boolean,
    onAppSelect: () -> Unit,
    setName: (String) -> Unit,
    setStartTime: (LocalTime) -> Unit,
    setEndTime: (LocalTime) -> Unit,
    setPushEnabled: (Boolean) -> Unit,
    onSelectDay: (DayOfWeek) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            AppSelectButton(
                modifier = Modifier.padding(top = 20.dp),
                onClick = onAppSelect,
            )
            RoutineNameContent(
                name = name,
                isPushEnabled = isPushEnabled,
                setName = setName,
                setPushEnabled = setPushEnabled,
            )
            RoutineTimeContent(
                startTime = startTime,
                endTime = endTime,
                setStartTime = setStartTime,
                setEndTime = setEndTime,
            )
            RoutineDayContent(
                selectDays = selectDays,
                onSelectDay = onSelectDay
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        KeepButton(
            modifier = Modifier.fillMaxWidth(),
            text = "루틴 추가하기",
            enabled = isButtonEnabled,
            onClick = { },
        )
    }
}

@Composable
private fun RoutineAppSelectionContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row {
            IconButton(
                onClick = onBackClick,
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null,
                    tint = KeepTheme.colors.primary,
                )
            }
        }
        CategoryBottomSheetContent(
            storeSelectApps = emptySet(),
            onComplete = { }
        )
    }
}