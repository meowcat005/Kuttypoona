package com.lumeo.app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lumeo.app.data.AppPreferences
import kotlinx.coroutines.delay

@Composable fun DeepTimeScreen(prefs:AppPreferences,onChanged:()->Unit){var selected by remember{mutableIntStateOf(prefs.timerMinutes)};var now by remember{mutableLongStateOf(System.currentTimeMillis())};LaunchedEffect(prefs.timerRunning){while(prefs.timerRunning){now=System.currentTimeMillis();if(now>=prefs.timerEndAt){prefs.timerRunning=false;prefs.timerEndAt=0;prefs.awardXp(selected);prefs.focusMinutes=prefs.focusMinutes+selected;prefs.lastCompletedAt=now;onChanged();break};delay(500)}};val remaining=if(prefs.timerRunning)((prefs.timerEndAt-now+999)/1000).coerceAtLeast(0) else selected*60;val done=!prefs.timerRunning&&prefs.lastCompletedAt>System.currentTimeMillis()-15000;Column(Modifier.fillMaxSize().padding(24.dp),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Center){Text("DEEP TIME",style=MaterialTheme.typography.titleLarge);Spacer(Modifier.height(24.dp));Text(String.format("%02d:%02d",remaining/60,remaining%60),style=MaterialTheme.typography.displaySmall);Spacer(Modifier.height(8.dp));Text(if(done) "Focus session complete • XP earned" else "Do what matters.");if(!prefs.timerRunning){Spacer(Modifier.height(24.dp));Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){listOf(15,25,45).forEach{m->FilterChip(selected=selected==m,onClick={selected=m;prefs.timerMinutes=m},label={Text("${m}m")})}};Spacer(Modifier.height(24.dp));Button(onClick={prefs.timerMinutes=selected;prefs.timerEndAt=System.currentTimeMillis()+selected*60000L;prefs.timerRunning=true;onChanged()},shape=RoundedCornerShape(50)){Text("Start Deep Time")}}else{Spacer(Modifier.height(28.dp));OutlinedButton(onClick={prefs.timerRunning=false;prefs.timerEndAt=0;onChanged()}){Text("End session")}}}}
