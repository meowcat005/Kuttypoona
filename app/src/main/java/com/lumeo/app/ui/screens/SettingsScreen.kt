package com.lumeo.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var showCompanion by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        SectionLabel("Buddy")
        ToggleRow("Show companion", showCompanion) { showCompanion = it }

        Spacer(Modifier.height(16.dp))
        SectionLabel("Usage")
        Text("Screen-time goal: ${formatMinutes(dummyGoalMin)}", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(16.dp))
        SectionLabel("Appearance")
        ToggleRow("Dark mode", darkMode) { darkMode = it }

        Spacer(Modifier.height(16.dp))
        SectionLabel("Privacy")
        Text(
            "All usage data stays on this device in Phase 1. Nothing is uploaded.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(text, style = MaterialTheme.typography.titleLarge)
    Spacer(Modifier.height(6.dp))
}

@Composable
private fun ToggleRow(label: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge)
        Switch(checked = checked, onCheckedChange = onChange)
    }
}
