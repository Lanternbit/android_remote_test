package com.example.test_media_projection.ui

import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.test_media_projection.viewmodel.SharedViewModel

@Composable
fun DisplayScreen (sharedViewModel: SharedViewModel) {
    AndroidView(
        factory = { context ->
            SurfaceView(context).apply {
                holder.addCallback(object : SurfaceHolder.Callback {
                    override fun surfaceCreated(holder: SurfaceHolder) {
                        sharedViewModel.setDisplaySurface(holder.surface)
                    }

                    override fun surfaceChanged(
                        holder: SurfaceHolder,
                        format: Int,
                        width: Int,
                        height: Int
                    ) {}

                    override fun surfaceDestroyed(holder: SurfaceHolder) {}
                })
            }
        }
    )
}