package com.uiery.keep.datastore

interface LocalDeviceDataStore {
    suspend fun saveDeviceToken(deviceToken: String)
}