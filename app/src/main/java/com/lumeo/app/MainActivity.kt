package com.lumeo.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.lumeo.app.data.AppPreferences
import com.lumeo.app.data.UsageRepository
import com.lumeo.app.overlay.CompanionOverlayService
import com.lumeo.app.ui.LumeoNavHost
import com.lumeo.app.ui.theme.LumeoTheme

class MainActivity : ComponentActivity() {
    lateinit var prefs: AppPreferences
    lateinit var usage: UsageRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = AppPreferences(this); usage = UsageRepository(this)
        setContent { var darkMode by remember { mutableStateOf(prefs.darkMode) }; LumeoTheme(darkMode = darkMode) { LumeoNavHost(this, prefs, usage, onDarkModeChanged = { darkMode = it }) } }
    }
    override fun onResume() { super.onResume() }
    fun openUsageAccess() { startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)) }
    fun openOverlayAccess() { startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))) }
    fun startCompanion() { if (Settings.canDrawOverlays(this)) { val i=Intent(this, CompanionOverlayService::class.java); if (android.os.Build.VERSION.SDK_INT>=26) startForegroundService(i) else startService(i) } }
    fun stopCompanion() { stopService(Intent(this, CompanionOverlayService::class.java)) }
}
