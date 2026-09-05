package com.lumeo.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeepTimeScreen() {
    var selectedMinutes by remember { mutableStateOf(25) }
    var running by remember { mutableStateOf(false) }
    var completed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("DEEP TIME", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(24.dp))

        Text(
            text = if (completed) "Completed 🎉" else String.format("%02d:00", selectedMinutes),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(8.dp))
        Text(
            text = if (completed) "XP +$selectedMinutes" else "\"Do what matters.\"",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(24.dp))

        if (!running && !completed) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(15, 25, 45).forEach { mins ->
                    FilterChip(
                        selected = selectedMinutes == mins,
                        onClick = { selectedMinutes = mins },
                        label = { Text("${mins}m") }
                    )
                }
            }
            Spacer(Modifier.height(24.dp))
        }

        Button(
            onClick = {
                if (completed) {
                    completed = false
                } else if (running) {
                    running = false
                    completed = true // Phase 1: instantly "complete" for demo purposes
                } else {
                    running = true
                }
            },
            shape = RoundedCornerShape(50)
        ) {
            Text(
                when {
                    completed -> "Start Another"
                    running -> "Finish (demo)"
                    else -> "Start Deep Time"
                }
            )
        }
    }
}
