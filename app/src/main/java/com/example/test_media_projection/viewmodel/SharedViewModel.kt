package com.example.test_media_projection.viewmodel

import android.content.Context
import android.content.Intent
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.view.Surface
import androidx.lifecycle.ViewModel
import com.example.test_media_projection.util.MediaProjectionHelper

class SharedViewModel : ViewModel() {
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private var displaySurface: Surface? = null

    fun startMediaProjection (context: Context, resultCode: Int, data: Intent) {
        mediaProjection = MediaProjectionHelper.createMediaProjection(context, resultCode, data)
        displaySurface?.let { surface ->
            virtualDisplay = MediaProjectionHelper.createVirtualDisplay(mediaProjection!!, surface)
        }
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
        virtualDisplay?.release()
        mediaProjection?.stop()
        super.onCleared()
    }
}