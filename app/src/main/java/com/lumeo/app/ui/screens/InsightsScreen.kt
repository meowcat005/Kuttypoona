package com.lumeo.app.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lumeo.app.data.*
import com.lumeo.app.ui.theme.SageGreen

@Composable fun InsightsScreen(repo:UsageRepository,refresh:Int){var snap by remember{mutableStateOf(repo.snapshot())};LaunchedEffect(refresh){snap=repo.snapshot()};Column(Modifier.fillMaxSize().padding(20.dp)){Text("Your Rhythm",style=MaterialTheme.typography.headlineMedium);Spacer(Modifier.height(16.dp));Text("This week",style=MaterialTheme.typography.titleMedium);Spacer(Modifier.height(8.dp));if(!repo.hasUsageAccess()) Text("Grant Usage access from Today to unlock real insights.") else if(snap.weekly.isNotEmpty()){val max=maxOf(1,snap.weekly.maxOf{it.second});Row(Modifier.fillMaxWidth().height(180.dp),horizontalArrangement=Arrangement.spacedBy(8.dp)){snap.weekly.forEach{(day,m)->Column(Modifier.weight(1f).fillMaxHeight(),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Bottom){Box(Modifier.fillMaxWidth().fillMaxHeight((m.toFloat()/max).coerceAtLeast(.03f)).background(SageGreen,RoundedCornerShape(8.dp)));Spacer(Modifier.height(5.dp));Text(day.take(3))}}}};Spacer(Modifier.height(24.dp));Text("Most-used apps",style=MaterialTheme.typography.titleMedium);snap.topApps.forEach{AppRow(it)}}}
