package com.lumeo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lumeo.app.ui.theme.SageGreen

@Composable
fun InsightsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text("Your Rhythm", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Text("This week", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(8.dp))

        val max = dummyWeek.maxOf { it.second }
        Row(
            modifier = Modifier.fillMaxWidth().height(160.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            dummyWeek.forEach { (day, minutes) ->
                Column(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    val heightFraction = minutes / max.toFloat()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(heightFraction)
                            .background(SageGreen, RoundedCornerShape(8.dp))
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(day, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Most-used apps", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(8.dp))

        dummyTopApps.forEach { app ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(app.appName)
                    Text(formatMinutes(app.minutes))
                }
            }
        }
    }
}
