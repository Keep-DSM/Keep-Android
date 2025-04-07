package com.uiel.keep.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.uiel.keep.KeepDataSource
import javax.inject.Inject

class LocalDeviceDataStoreImpl @Inject constructor(
    @KeepDataSource private val dataStore: DataStore<Preferences>
): LocalDeviceDataStore{
    override suspend fun saveDeviceToken(deviceToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.FCM_TOKEN] = deviceToken
        }
    }
}