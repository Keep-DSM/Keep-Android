package com.uiel.keep

import com.uiel.keep.datastore.LocalDeviceDataStore
import javax.inject.Inject

class DeviceTokenManager @Inject constructor(
    private val localDeviceDataStore: LocalDeviceDataStore,
) {
    suspend fun saveDeviceToken(deviceToken: String) {
        localDeviceDataStore.saveDeviceToken(deviceToken)
    }
}