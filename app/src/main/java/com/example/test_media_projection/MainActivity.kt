package com.example.test_media_projection

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test_media_projection.ui.CaptureScreen
import com.example.test_media_projection.viewmodel.SharedViewModel
import com.example.test_media_projection.ui.DisplayScreen
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private var isMultiWindow by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            observeMultiWindowMode()
            Surface (
                modifier = Modifier.fillMaxSize()
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    UIHandler()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, ScreenCaptureService::class.java))
    }

    private fun observeMultiWindowMode() {
        isMultiWindow = isInMultiWindowMode
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean, newConfig: Configuration) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig)
        isMultiWindow = isInMultiWindowMode
        setContent {
            Surface (
                modifier = Modifier.fillMaxSize()
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    UIHandler()
                }
            }
        }
    }

    @Composable
    private fun UIHandler() {
        val sharedViewModel: SharedViewModel = viewModel()

        if (isMultiWindow) {
            DisplayScreen(sharedViewModel)
            Log.d("Screen Check", "Display Mode")
        } else {
            CaptureScreen(sharedViewModel)
            Log.d("Screen Check", "Capture Mode")
        }
    }
}