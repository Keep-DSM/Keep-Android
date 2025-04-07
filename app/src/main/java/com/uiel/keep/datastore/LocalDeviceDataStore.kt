package com.uiel.keep.datastore

interface LocalDeviceDataStore {
    suspend fun saveDeviceToken(deviceToken: String)
}