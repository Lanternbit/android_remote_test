package com.example.test_media_projection.ui

import android.app.Activity
import android.media.projection.MediaProjectionManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.test_media_projection.viewmodel.SharedViewModel

@Composable
fun CaptureScreen(sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val mediaProjectionManager = remember { context.getSystemService(MediaProjectionManager::class.java) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            sharedViewModel.startMediaProjection(
                context,
                result.resultCode,
                result.data!!
            )
        }
    }
    Button(onClick = {
        launcher.launch(mediaProjectionManager.createScreenCaptureIntent())
    }) {
        Text("Start Screen Capture!")
    }
}