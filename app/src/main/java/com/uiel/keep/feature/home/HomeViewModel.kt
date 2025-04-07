package com.uiel.keep.feature.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import com.uiel.keep.KeepDataSource
import com.uiel.keep.datastore.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @KeepDataSource private val dataStore: DataStore<Preferences>,
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeUiState, HomeSideEffect> = container(HomeUiState())

    init {
        getIsKeep()
        getSelectedApp()
    }

    internal fun changeIsKeep() = intent {
        reduce { state.copy(isKeep = !state.isKeep, startTime = System.currentTimeMillis()) }
        storeIsKeep()
        if (state.isKeep) {
            storeStartTime()
        }
    }

    internal fun showSnackBar(message: String) = intent {
        postSideEffect(HomeSideEffect.ShowSnackBar(message))
        CoroutineScope(Dispatchers.IO).launch {
            reduce { state.copy(snackbarMessage = message) }
        }
    }

    internal fun showCategoryBottomSheet() = intent {
        reduce {
            state.copy(
                isShowCategoryBottomSheet = true,
            )
        }
    }

    internal fun showTimeBottomSheet() = intent {
        reduce {
            state.copy(isShowTimeBottomSheet = true)
        }
    }

    internal fun hideCategoryBottomSheet() = intent {
        reduce { state.copy(isShowCategoryBottomSheet = false)}
    }

    internal fun hideTimeBottomSheet() = intent {
        reduce { state.copy(isShowTimeBottomSheet = false) }
    }

    internal fun moveToLock() = intent {
        val lockTime = state.blockTime.atDate(LocalDate.now()).toString()
        postSideEffect(HomeSideEffect.MoveToLock(lockTime))
    }

    private fun getSelectedApp() = intent {
        val selectedAppPackage = dataStore.data.map { data ->
            data[PreferencesKey.SELECTED_APP_PACKAGES].orEmpty()
        }.firstOrNull()
        selectedAppPackage?.let {
            reduce { state.copy(selectedAppPackage = it) }
        }
    }

    private fun storeSelectedApp(selectedAppPackage: Set<String>) = intent {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.SELECTED_APP_PACKAGES] = selectedAppPackage
        }
    }

    internal fun selectCategoryComplete(selectedAppPackage: Set<String>) = intent {
        storeSelectedApp(selectedAppPackage)
        reduce { state.copy(selectedAppPackage = selectedAppPackage) }
    }

    private fun storeIsKeep() = intent {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.IS_KEEP] = state.isKeep
        }
    }

    private fun storeStartTime() = intent {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.START_TIME] = System.currentTimeMillis()
        }
    }

    private fun getStartTime() = intent {
        val startTime = dataStore.data.map { preferences ->
            preferences[PreferencesKey.START_TIME]
        }.firstOrNull()
        reduce { state.copy(startTime = startTime ?: System.currentTimeMillis()) }
    }

    private fun getIsKeep() = intent {
        val isKeep = dataStore.data.map { preferences ->
            preferences[PreferencesKey.IS_KEEP]
        }.firstOrNull()
        reduce { state.copy(isKeep = isKeep == true) }
        if (isKeep == true) {
            getStartTime()
        }
    }

    internal fun updateCountdownTime(countdownTime: LocalTime) = intent {
        val blockTime = LocalTime.now().plusHours(countdownTime.hour.toLong()).plusMinutes(countdownTime.minute.toLong())
        reduce { state.copy(countdownTime = countdownTime,blockTime = blockTime) }
    }

    internal fun updateTimerTime(timerTime: LocalTime) = intent {
        reduce { state.copy(timerTime = timerTime, blockTime = timerTime) }
    }

    internal fun lockTime() = intent {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.LOCK_TIME] = state.blockTime.atDate(LocalDate.now()).toString()
        }
    }
}

data class HomeUiState(
    val isKeep: Boolean = false,
    val snackbarMessage: String = "",
    val isShowCategoryBottomSheet: Boolean = false,
    val isShowTimeBottomSheet: Boolean = false,
    val selectedAppPackage: Set<String> = emptySet(),
    val startTime: Long = System.currentTimeMillis(),
    val searchContent: String = "",
    val isSelectAll: Boolean  = true,
    val blockTime: LocalTime = LocalTime.now(),
    val countdownTime: LocalTime = LocalTime.now(),
    val timerTime: LocalTime = LocalTime.now(),
)

sealed class HomeSideEffect {
    data class ShowSnackBar(val message: String): HomeSideEffect()
    data class MoveToLock(val lockTime: String): HomeSideEffect()
}