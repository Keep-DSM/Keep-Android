package com.uiel.keep.feature.onboarding.select

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import com.uiel.keep.KeepDataSource
import com.uiel.keep.datastore.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SelectAppViewModel @Inject constructor(
    @KeepDataSource private val dataStore: DataStore<Preferences>,
) : ContainerHost<SelectAppUiState, SelectAppSideEffect>, ViewModel() {
    override val container: Container<SelectAppUiState, SelectAppSideEffect> =
        container(SelectAppUiState())

    internal fun showCategoryBottomSheet() = intent {
        reduce { state.copy(isShowCategoryBottomSheet = true) }
    }

    internal fun hideCategoryBottomSheet() = intent {
        reduce { state.copy(isShowCategoryBottomSheet = false)}
    }

    internal fun selectCategoryComplete(selectedAppPackage: Set<String>) = intent {
        storeSelectedApp(selectedAppPackage)
        storeIsNew()
    }

    private fun storeSelectedApp(selectedAppPackage: Set<String>) = intent {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.SELECTED_APP_PACKAGES] = selectedAppPackage
        }
    }

    private fun storeIsNew() = intent {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.IS_NEW] = false
        }
    }
}

data class SelectAppUiState(
    val isShowCategoryBottomSheet: Boolean = false,
)

sealed class SelectAppSideEffect