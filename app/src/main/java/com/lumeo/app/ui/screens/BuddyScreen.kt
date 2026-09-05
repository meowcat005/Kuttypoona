package com.lumeo.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BuddyScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("My Buddy", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        Card(shape = RoundedCornerShape(24.dp), modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("🌿", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(8.dp))
                Text(dummyBuddyName, style = MaterialTheme.typography.titleLarge)
                Text("Level $dummyBuddyLevel")
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = dummyBuddyXp / dummyBuddyXpNeeded.toFloat(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(4.dp))
                Text("$dummyBuddyXp / $dummyBuddyXpNeeded XP", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(Modifier.height(24.dp))
        listOf("Character selection", "Accessories", "Background").forEach { label ->
            OutlinedButton(
                onClick = { /* Phase 1: no-op, wire up in later phase */ },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) { Text(label) }
        }
    }
}
