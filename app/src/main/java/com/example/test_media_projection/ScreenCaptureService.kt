package com.example.test_media_projection

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ScreenCaptureService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        val notification = createNotification()

        // Version 10, API 29 이상부터는 foreground service로 실행되어야 함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION)
        } else {
            startForeground(1, notification)
        }
    }

    private fun createNotification() : Notification {
        val channelID = "media_projection_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val channel = NotificationChannel(
                channelID,
                "Screen Capture",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelID)
            .setContentTitle("화면 캡처 중..")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
    }
}