package com.uiery.keep

import android.app.Application
import android.util.Log
import com.datadog.android.Datadog
import com.datadog.android.DatadogSite
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.Rum
import com.datadog.android.rum.RumConfiguration
import com.datadog.android.rum.tracking.ActivityViewTrackingStrategy
import com.datadog.android.sessionreplay.ImagePrivacy
import com.datadog.android.sessionreplay.SessionReplay
import com.datadog.android.sessionreplay.SessionReplayConfiguration
import com.datadog.android.sessionreplay.SessionReplayPrivacy
import com.datadog.android.sessionreplay.TextAndInputPrivacy
import com.datadog.android.sessionreplay.TouchPrivacy
import com.datadog.android.sessionreplay.compose.ComposeExtensionSupport
import com.datadog.android.sessionreplay.material.MaterialExtensionSupport
import com.uiery.keep.util.isTestMode
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
            variant = appVariantName,
            service = "Keep",
        )
            .useSite(DatadogSite.US5)
            .build()

        Datadog.initialize(
            context = this,
            configuration= configuration,
            trackingConsent = TrackingConsent.GRANTED
        )

        val applicationId = BuildConfig.DATA_DOG_APPLICATION_ID
        val rumConfiguration = RumConfiguration.Builder(applicationId)
            .trackUserInteractions()
            //.trackLongTasks(durationThreshold)
            .trackFrustrations(true)
            .useViewTrackingStrategy(ActivityViewTrackingStrategy(true))
            .build()
        Rum.enable(rumConfiguration)

        val sessionReplayConfig = SessionReplayConfiguration.Builder(100f)
            .addExtensionSupport(MaterialExtensionSupport())
            .addExtensionSupport(ComposeExtensionSupport())
            .setPrivacy(SessionReplayPrivacy.ALLOW)
            .setImagePrivacy(ImagePrivacy.MASK_NONE)
            .setTouchPrivacy(TouchPrivacy.SHOW)
            .setTextAndInputPrivacy(TextAndInputPrivacy.MASK_SENSITIVE_INPUTS)
            .build()
        SessionReplay.enable(sessionReplayConfig)

        Datadog.setVerbosity(Log.DEBUG)
    }
}