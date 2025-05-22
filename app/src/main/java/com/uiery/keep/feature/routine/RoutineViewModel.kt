package com.uiery.keep.feature.routine

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(

): ContainerHost<RoutineUiState,RoutineSideEffect>, ViewModel(){
    override val container: Container<RoutineUiState, RoutineSideEffect> = container(RoutineUiState())

    internal fun setName(name: String) = intent {
        reduce { state.copy(name = name) }
        setButtonEnabled()
    }

    internal fun showRoutineBottomSheet() = intent {
        reduce { state.copy(isShowRoutineBottomSheet = true) }
    }

    internal fun hideRoutineBottomSheet() = intent {
        reduce { state.copy(isShowRoutineBottomSheet = false) }
    }

    internal fun setStartTime(startTime: LocalTime) = intent {
        reduce { state.copy(startTime = startTime) }
        setButtonEnabled()
    }

    internal fun setEndTime(endTime: LocalTime) = intent {
        reduce { state.copy(endTime = endTime) }
        setButtonEnabled()
    }

    internal fun setSelectDays(dayOfWeek: DayOfWeek) = intent {
        val selectDays = state.selectDays
        val updatedDays = if (selectDays.contains(dayOfWeek)) {
            selectDays.minus(dayOfWeek)
        } else {
            selectDays.plus(dayOfWeek)
        }
        reduce { state.copy(selectDays = updatedDays) }
        setButtonEnabled()
    }

    private fun setButtonEnabled() = intent {
        val isNameValid = state.name.isNotEmpty()
        val isTimeValid = state.startTime.isBefore(state.endTime) &&
                java.time.Duration.between(state.startTime, state.endTime).toMinutes() >= 15
        val isDaySelected = state.selectDays.isNotEmpty()
        val isEnabled = isNameValid && isTimeValid && isDaySelected
        reduce { state.copy(isButtonEnable = isEnabled) }
    }

    internal fun setPushEnabled(isPushEnabled: Boolean) = intent {
        reduce { state.copy(isPushEnabled = isPushEnabled) }
    }
}

data class RoutineUiState(
    val name: String = "",
    val isShowRoutineBottomSheet: Boolean = false,
    val startTime: LocalTime = LocalTime.now(),
    val endTime: LocalTime = LocalTime.now(),
    val selectDays: List<DayOfWeek> = emptyList(),
    val isPushEnabled: Boolean = false,
    val isButtonEnable: Boolean = false,
)

sealed class RoutineSideEffect {
}