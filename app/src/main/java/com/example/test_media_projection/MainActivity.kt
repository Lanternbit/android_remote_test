package com.example.test_media_projection

import android.os.Bundle
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
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface (
                modifier = Modifier.fillMaxSize()
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val sharedViewModel: SharedViewModel = viewModel()
                    if (isInMultiWindowMode) {
                        DisplayScreen(sharedViewModel)
                    } else {
                        CaptureScreen(sharedViewModel)
                    }
                }
            }
        }
    }
}