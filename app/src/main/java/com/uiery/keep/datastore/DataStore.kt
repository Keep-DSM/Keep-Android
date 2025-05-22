package com.uiery.keep.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "keep-datastore")

object PreferencesKey {
    val SELECTED_APP_PACKAGES = stringSetPreferencesKey("selected_app_packages")
    val IS_KEEP = booleanPreferencesKey("is_keep")
    val FCM_TOKEN = stringPreferencesKey("fcm_token")
    val START_TIME = longPreferencesKey("start_time")
    val LOCK_TIME = stringPreferencesKey("lock_time")
    val IS_NEW = booleanPreferencesKey("is_new")
}