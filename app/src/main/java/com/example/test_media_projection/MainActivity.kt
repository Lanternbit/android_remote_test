package com.example.test_media_projection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test_media_projection.ui.CaptureScreen
import com.example.test_media_projection.viewmodel.SharedViewModel
import com.example.test_media_projection.ui.DisplayScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedViewModel: SharedViewModel = viewModel()
            if (isInMultiWindowMode) {
                DisplayScreen(sharedViewModel)
            } else {
                CaptureScreen(sharedViewModel)
            }
        }
    }
}