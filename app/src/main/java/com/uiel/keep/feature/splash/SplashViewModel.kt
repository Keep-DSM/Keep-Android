package com.uiel.keep.feature.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.uiel.keep.KeepDataSource
import com.uiel.keep.datastore.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @KeepDataSource private val dataStore: DataStore<Preferences>,
) : ContainerHost<SplashUiState, SplashSideEffect>, ViewModel() {

    override val container: Container<SplashUiState, SplashSideEffect> = container(SplashUiState())

    init {
        getIsNew()
        getLockTime()
        navigateScreen()
    }

    private fun navigateScreen() = intent {
        delay(700)
        if (state.isNew) {
            postSideEffect(SplashSideEffect.MoveToOnboarding)
        } else if (state.isLock) {
            val lockTime = state.lockTime
            postSideEffect(SplashSideEffect.MoveToLock(lockTime = lockTime))
        } else {
            postSideEffect(SplashSideEffect.MoveToHome)
        }
    }

    private fun getIsNew() = intent {
        val isNew = dataStore.data.map { preferences ->
            preferences[PreferencesKey.IS_NEW]
        }.firstOrNull()
        reduce { state.copy(isNew = isNew ?: false) }
    }

    private fun getLockTime() = intent {
        val lockTime = dataStore.data.map { preferences ->
            preferences[PreferencesKey.LOCK_TIME]
        }.firstOrNull()

        lockTime?.let {
            val isLock = LocalDateTime.now() < LocalDateTime.parse(it)
            reduce { state.copy(isLock = isLock, lockTime = it) }
        }
    }
}

data class SplashUiState(
    val isNew: Boolean = false,
    val isLock: Boolean = false,
    val lockTime: String = LocalDateTime.now().toString(),
)

sealed class SplashSideEffect {
    data object MoveToHome : SplashSideEffect()
    data object MoveToOnboarding : SplashSideEffect()
    data class MoveToLock(val lockTime: String) : SplashSideEffect()
}