package com.lumeo.app.ui.screens

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lumeo.app.data.*

@Composable fun TodayScreen(activity: Activity,prefs:AppPreferences,repo:UsageRepository,refresh:Int){ var snap by remember{mutableStateOf(repo.snapshot())}; LaunchedEffect(refresh){snap=repo.snapshot()}; Column(Modifier.fillMaxSize().padding(20.dp)){ Text("Today",style=MaterialTheme.typography.headlineMedium); Spacer(Modifier.height(16.dp));
    if(!repo.hasUsageAccess()) PermissionCard("Usage access is needed","Kuttypoona reads on-device app usage to show real screen time. Nothing is uploaded."){ activity.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)) };
    StatCard("Screen Time",formatMinutes(snap.totalMinutes),"Goal: ${formatMinutes(prefs.goalMinutes)}"); StatCard("Focus Time",formatMinutes(prefs.focusMinutes),"Deep Time completed on this device"); StatCard("Unlocks",snap.unlocks.toString(),"");
    val delta=snap.totalMinutes-snap.yesterdayMinutes; StatCard("Compared with yesterday",if(delta<=0) "${-delta} minutes less" else "${delta} minutes more","Live on-device usage")
    Spacer(Modifier.height(12.dp)); if(snap.topApps.isNotEmpty()){Text("Most-used apps",style=MaterialTheme.typography.titleMedium); snap.topApps.take(5).forEach{AppRow(it)} } else Text("Use a few apps and come back here to see your real usage.")
} }
@Composable private fun StatCard(title:String,value:String,sub:String){Card(Modifier.fillMaxWidth().padding(vertical=6.dp),shape=RoundedCornerShape(22.dp)){Column(Modifier.padding(20.dp)){Text(title,style=MaterialTheme.typography.bodyLarge);Text(value,style=MaterialTheme.typography.headlineMedium);if(sub.isNotBlank())Text(sub)}}}
@Composable fun AppRow(a:AppUsage){Card(Modifier.fillMaxWidth().padding(vertical=4.dp),shape=RoundedCornerShape(16.dp)){Row(Modifier.fillMaxWidth().padding(14.dp),horizontalArrangement=Arrangement.SpaceBetween){Text(a.label);Text(formatMinutes(a.minutes))}}}
@Composable private fun PermissionCard(title:String,body:String,onClick:()->Unit){Card(Modifier.fillMaxWidth().padding(bottom=10.dp),shape=RoundedCornerShape(20.dp)){Column(Modifier.padding(18.dp)){Text(title,style=MaterialTheme.typography.titleMedium);Spacer(Modifier.height(4.dp));Text(body,style=MaterialTheme.typography.bodyMedium);Spacer(Modifier.height(10.dp));Button(onClick=onClick){Text("Grant access")}}}}
