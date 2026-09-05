package com.lumeo.app.ui.screens
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lumeo.app.MainActivity
import com.lumeo.app.data.*

@Composable fun SettingsScreen(activity:Activity,prefs:AppPreferences,repo:UsageRepository,onDarkModeChanged:(Boolean)->Unit,onChanged:()->Unit){var companion by remember{mutableStateOf(prefs.showCompanion)};var dark by remember{mutableStateOf(prefs.darkMode)};Column(Modifier.fillMaxSize().padding(20.dp)){Text("Settings",style=MaterialTheme.typography.headlineMedium);Spacer(Modifier.height(18.dp));Text("Buddy",style=MaterialTheme.typography.titleLarge);Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text("Show companion");Switch(companion,{companion=it;prefs.showCompanion=it;if(activity is MainActivity){if(it)activity.startCompanion() else activity.stopCompanion()}})};Spacer(Modifier.height(20.dp));Text("Usage",style=MaterialTheme.typography.titleLarge);Text("Screen-time goal: ${formatMinutes(prefs.goalMinutes)}");Button(onClick={prefs.goalMinutes=(prefs.goalMinutes+30).let{if(it>600)60 else it};onChanged()}){Text("Change goal (${formatMinutes(prefs.goalMinutes+30)})")};Spacer(Modifier.height(20.dp));Text("Appearance",style=MaterialTheme.typography.titleLarge);Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text("Dark mode");Switch(dark,{dark=it;prefs.darkMode=it;onDarkModeChanged(it)})};Spacer(Modifier.height(20.dp));Text("Permissions",style=MaterialTheme.typography.titleLarge);Button(onClick={activity.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))}){Text(if(repo.hasUsageAccess())"Usage access granted" else "Grant usage access")};if(!Settings.canDrawOverlays(activity)){OutlinedButton(onClick={activity.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${activity.packageName}")))}){Text("Allow companion overlay")}}else Text("Companion overlay: allowed");Spacer(Modifier.height(20.dp));Text("Privacy",style=MaterialTheme.typography.titleLarge);Text("Usage statistics stay on this device. Kuttypoona does not upload your app-usage history.")}}
