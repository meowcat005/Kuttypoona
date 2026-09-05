package com.lumeo.app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lumeo.app.data.AppPreferences

@Composable fun BuddyScreen(prefs:AppPreferences){Column(Modifier.fillMaxSize().padding(20.dp),horizontalAlignment=Alignment.CenterHorizontally){Text("My Buddy",style=MaterialTheme.typography.headlineMedium);Spacer(Modifier.height(24.dp));Card(Modifier.fillMaxWidth(),shape=RoundedCornerShape(24.dp)){Column(Modifier.padding(22.dp),horizontalAlignment=Alignment.CenterHorizontally){Text("🌿",style=MaterialTheme.typography.headlineLarge);Text(prefs.buddyName,style=MaterialTheme.typography.titleLarge);Text("Level ${prefs.level}");Spacer(Modifier.height(10.dp));LinearProgressIndicator(progress=prefs.xp/200f,Modifier.fillMaxWidth());Spacer(Modifier.height(4.dp));Text("${prefs.xp} / 200 XP")}};Spacer(Modifier.height(24.dp));OutlinedButton(onClick={}){Text("Character selection")};OutlinedButton(onClick={}){Text("Accessories")};OutlinedButton(onClick={}){Text("Background")}}}
