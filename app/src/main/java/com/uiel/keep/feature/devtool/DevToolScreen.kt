package com.uiel.keep.feature.devtool

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.uiel.keep.feature.devtool.component.DevToolItem
import com.uiel.keep.util.deviceId
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun DevToolScreen(
    modifier: Modifier = Modifier,
    viewModel: DevToolViewModel = hiltViewModel(),
) {
    val uiState by viewModel.collectAsState()

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            DevToolItem(
                title = "Device ID",
                content = deviceId(),
            )
            DevToolItem(
                title = "FCM Token",
                content = uiState.fcmToken,
            )
        }
    }
}