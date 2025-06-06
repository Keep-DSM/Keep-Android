package com.uiery.keep.datastore

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDeviceDataStoreModule {
    @Binds
    @Singleton
    abstract fun bindLocalDeviceDataStore(localDeviceDataStoreImpl: LocalDeviceDataStoreImpl): LocalDeviceDataStore
}