package com.lumeo.app.overlay

import android.app.*
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.view.*
import android.widget.TextView
import androidx.core.app.NotificationCompat
import com.lumeo.app.R

class CompanionOverlayService : Service() {
    private var windowManager: WindowManager? = null
    private var bubble: View? = null
    override fun onCreate() {
        super.onCreate()
        if (!Settings.canDrawOverlays(this)) { stopSelf(); return }
        createChannel()
        startForeground(1001, NotificationCompat.Builder(this, "companion")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Kuttypoona companion")
            .setContentText("Your focus buddy is active")
            .setOngoing(true).build())
        showBubble()
    }
    private fun showBubble() {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val tv = TextView(this).apply { text = "🌿"; textSize = 28f; setPadding(22,14,22,14); setBackgroundColor(0xFFE9E0F0.toInt()) }
        tv.setOnClickListener { stopSelf() }
        val type = if (Build.VERSION.SDK_INT >= 26) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY else WindowManager.LayoutParams.TYPE_PHONE
        val lp = WindowManager.LayoutParams(WRAP, WRAP, type, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT)
        lp.gravity = Gravity.END or Gravity.CENTER_VERTICAL; lp.x = 20
        windowManager?.addView(tv, lp); bubble = tv
    }
    override fun onDestroy() { bubble?.let { windowManager?.removeView(it) }; bubble = null; super.onDestroy() }
    override fun onBind(intent: Intent?): IBinder? = null
    private fun createChannel() { if (Build.VERSION.SDK_INT >= 26) getSystemService(NotificationManager::class.java).createNotificationChannel(NotificationChannel("companion", "Companion", NotificationManager.IMPORTANCE_LOW)) }
    companion object { private const val WRAP = WindowManager.LayoutParams.WRAP_CONTENT }
}
