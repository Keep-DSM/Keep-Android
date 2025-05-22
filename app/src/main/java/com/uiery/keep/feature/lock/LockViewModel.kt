package com.uiery.keep.feature.lock

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.uiery.keep.KeepDataSource
import com.uiery.keep.datastore.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class LockViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @KeepDataSource private val dataStore: DataStore<Preferences>,
) : ContainerHost<LockUiState,LockSideEffect>, ViewModel(){

    private val route = savedStateHandle.toRoute<LockRoute>()
    override val container: Container<LockUiState, LockSideEffect> = container(LockUiState(
        lockTime = LocalDateTime.parse(route.lockTime)
    ))

    init {
        getSelectedApp()
        navigateHome()
    }

    private fun getSelectedApp() = intent {
        val selectedAppPackage = dataStore.data.map { data ->
            data[PreferencesKey.SELECTED_APP_PACKAGES].orEmpty()
        }.firstOrNull()
        selectedAppPackage?.let {
            reduce { state.copy(selectedAppPackage = it) }
        }
    }

    private fun navigateHome() = intent {
        val now = LocalDateTime.now()
        val duration = Duration.between(now, state.lockTime).coerceAtLeast(Duration.ZERO)
        delay(duration.toMillis())
        postSideEffect(LockSideEffect.MoveToHome)
    }
}

data class LockUiState(
    val lockTime: LocalDateTime = LocalDateTime.now(),
    val selectedAppPackage: Set<String> = emptySet(),
)

sealed class LockSideEffect {
    data object MoveToHome: LockSideEffect()
}