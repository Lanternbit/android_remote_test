package com.example.test_media_projection.viewmodel

import android.content.Context
import android.content.Intent
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.view.Surface
import androidx.lifecycle.ViewModel
import com.example.test_media_projection.ScreenCaptureService
import com.example.test_media_projection.util.MediaProjectionHelper
import android.os.Build
import android.os.Handler
import android.os.Looper

class SharedViewModel : ViewModel() {
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private var displaySurface: Surface? = null

    private fun releaseResources() {
        virtualDisplay?.release()
        mediaProjection?.stop()
        virtualDisplay = null
        mediaProjection = null
    }

    fun startMediaProjection (context: Context, resultCode: Int, data: Intent) {
        val serviceIntent = Intent(context, ScreenCaptureService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            mediaProjection = MediaProjectionHelper.createMediaProjection(context, resultCode, data)

            displaySurface?.let { surface ->
                virtualDisplay = MediaProjectionHelper.createVirtualDisplay(mediaProjection!!, surface)
            }
        }, 500)
    }

    fun setDisplaySurface (surface: Surface) {
        displaySurface = surface
        mediaProjection?.let { mp ->
            if (virtualDisplay == null) {
                virtualDisplay = MediaProjectionHelper.createVirtualDisplay(mp, surface)
            }
        }
    }

    override fun onCleared() {
        releaseResources()
        super.onCleared()
    }
}