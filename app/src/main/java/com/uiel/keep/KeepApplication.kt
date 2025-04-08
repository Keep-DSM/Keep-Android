package com.uiel.keep

import android.app.Application
import com.datadog.android.Datadog
import com.datadog.android.DatadogSite
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.Rum
import com.datadog.android.rum.RumConfiguration
import com.datadog.android.rum.tracking.ActivityViewTrackingStrategy
import com.uiel.keep.util.isTestMode
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KeepApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val clientToken = BuildConfig.DATA_DOG_CLIENT_TOKEN
        val environmentName = if(isTestMode()) "staging" else "production"
        val appVariantName = BuildConfig.BUILD_TYPE

        val configuration = Configuration.Builder(
            clientToken = clientToken,
            env = environmentName,
            variant = appVariantName
        )
            .useSite(DatadogSite.US5)
            .build()
        Datadog.initialize(this, configuration, TrackingConsent.NOT_GRANTED)

        val applicationId = BuildConfig.DATA_DOG_APPLICATION_ID
        val rumConfiguration = RumConfiguration.Builder(applicationId)
            .trackUserInteractions()
            //.trackLongTasks(durationThreshold)
            .useViewTrackingStrategy(ActivityViewTrackingStrategy(true))
            .build()
        Rum.enable(rumConfiguration)
    }
}