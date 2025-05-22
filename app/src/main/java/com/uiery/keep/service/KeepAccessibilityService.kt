package com.uiery.keep.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import com.uiery.keep.BlockActivity
import com.uiery.keep.datastore.PreferencesKey
import com.uiery.keep.datastore.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.coroutines.CoroutineContext

class KeepAccessibilityService : AccessibilityService(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onServiceConnected() {
        super.onServiceConnected()
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        launch {
            val packageName = event?.packageName?.toString()
            val isKeep = this@KeepAccessibilityService.dataStore.data.map { preferences ->
                preferences[PreferencesKey.IS_KEEP]
            }.firstOrNull()
            val lockTime = this@KeepAccessibilityService.dataStore.data.map { preferences ->
                preferences[PreferencesKey.LOCK_TIME]
            }.firstOrNull()
            val isLockTime = lockTime?.let { LocalDateTime.now().isBefore(LocalDateTime.parse(it)) } ?: false

            if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && isKeep == true || isLockTime) {
                    val selectedAppPackage =
                        this@KeepAccessibilityService.dataStore.data.map { preferences ->
                            preferences[PreferencesKey.SELECTED_APP_PACKAGES].orEmpty()
                        }.firstOrNull()
                    if (selectedAppPackage?.contains(packageName) == true) {
                        val intent = Intent(this@KeepAccessibilityService, BlockActivity::class.java)
                        intent.putExtra("package_name", packageName)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

            }
        }
    }

    override fun onInterrupt() {

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}