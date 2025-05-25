package com.uiery.keep

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.uiery.kds.theme.KeepTheme

class BlockActivity: ComponentActivity() {
    lateinit var pipBlocker: PipBlockerOverlay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val packageName = intent.getStringExtra("package_name") ?: ""

//        pipBlocker = PipBlockerOverlay(this,packageName, onClose = {
//                                        val homeIntent = Intent(Intent.ACTION_MAIN)
//                            homeIntent.addCategory(Intent.CATEGORY_HOME)
//                            homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                            startActivity(homeIntent)
//                            finishAffinity()
//        })
//        // 권한 확인 및 PIP 화면 차단
//        if (pipBlocker.hasOverlayPermission()) {
//            pipBlocker.blockPipScreen()
//        } else {
//            // 권한 요청
//            pipBlocker.requestOverlayPermission()
//        }
        enableEdgeToEdge()
        setContent {
            KeepTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = KeepTheme.colors.background,
                ) { innerPadding ->
                    BlockScreen(
                        modifier = Modifier.padding(innerPadding),
                        packageName = packageName,
                        onClose = {
                            val homeIntent = Intent(Intent.ACTION_MAIN)
                            homeIntent.addCategory(Intent.CATEGORY_HOME)
                            homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(homeIntent)
                            finishAffinity()
                        }
                    )
                }
            }
        }
    }
}