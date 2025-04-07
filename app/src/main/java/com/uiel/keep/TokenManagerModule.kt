package com.uiel.keep

import com.uiel.keep.datastore.LocalDeviceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenManagerModule {
    @Provides
    @Singleton
    fun provideTokenManager(localDeviceDataStore: LocalDeviceDataStore): DeviceTokenManager {
        return DeviceTokenManager(localDeviceDataStore)
    }
}