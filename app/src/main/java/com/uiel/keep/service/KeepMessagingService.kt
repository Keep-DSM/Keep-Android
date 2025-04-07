package com.uiel.keep.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.uiel.keep.DeviceTokenManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KeepMessagingService : FirebaseMessagingService() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface DeviceTokenManagerEntryPoint {
        fun deviceTokenManager(): DeviceTokenManager
    }

    private fun getDeviceTokenManager(): DeviceTokenManager {
        val entryPoint = EntryPointAccessors.fromApplication(
            applicationContext,
            DeviceTokenManagerEntryPoint::class.java
        )
        return entryPoint.deviceTokenManager()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        CoroutineScope(Dispatchers.IO).launch {
            getDeviceTokenManager().saveDeviceToken(token)
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}