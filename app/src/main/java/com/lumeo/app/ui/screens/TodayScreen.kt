package com.lumeo.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodayScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text("Today", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        StatCard("Screen Time", formatMinutes(dummyTodayScreenTimeMin), "Goal: ${formatMinutes(dummyGoalMin)}")
        Spacer(Modifier.height(12.dp))
        StatCard("Focus Time", formatMinutes(dummyFocusTimeMin), null)
        Spacer(Modifier.height(12.dp))
        StatCard("Unlocks", dummyUnlocks.toString(), null)
        Spacer(Modifier.height(12.dp))
        StatCard("Compared With Yesterday", "$dummyYesterdayDeltaMin minutes less", null)

        Spacer(Modifier.height(20.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🌱 $dummyBuddyName is happy with today's progress", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
private fun StatCard(title: String, value: String, subtitle: String?) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.headlineMedium)
            if (subtitle != null) {
                Spacer(Modifier.height(2.dp))
                Text(subtitle, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
