package com.example.test_media_projection.util

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.view.Surface

object  MediaProjectionHelper {
    fun createMediaProjection (context: Context, resultCode: Int, data: Intent): MediaProjection {
        val manager = context.getSystemService(MediaProjectionManager::class.java)
        return manager.getMediaProjection(resultCode, data)
    }

    fun createVirtualDisplay (
        mediaProjection: MediaProjection,
        surface: Surface,
        width: Int = Resources.getSystem().displayMetrics.widthPixels,
        height: Int = Resources.getSystem().displayMetrics.heightPixels,
        densityDpi: Int = Resources.getSystem().displayMetrics.densityDpi
    ): VirtualDisplay {
        return mediaProjection.createVirtualDisplay(
            "ScreenCapture",
            width,
            height,
            densityDpi,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            surface,
            null,
            null
        )
    }
}