package com.lumeo.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lumeo.app.ui.screens.*

private sealed class Dest(val route: String, val label: String) {
    object Today : Dest("today", "Today")
    object DeepTime : Dest("deep_time", "Deep Time")
    object Insights : Dest("insights", "Insights")
    object Me : Dest("me", "Me")
}

private val destinations = listOf(Dest.Today, Dest.DeepTime, Dest.Insights, Dest.Me)

@Composable
fun LumeoNavHost() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination

                destinations.forEach { dest ->
                    val selected = currentDestination?.hierarchy?.any { it.route == dest.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(dest.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { NavIcon(dest) },
                        label = { Text(dest.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Dest.Today.route,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(Dest.Today.route) { TodayScreen() }
            composable(Dest.DeepTime.route) { DeepTimeScreen() }
            composable(Dest.Insights.route) { InsightsScreen() }
            composable(Dest.Me.route) { MeTabScreen() }
        }
    }
}

@Composable
private fun NavIcon(dest: Dest) {
    val icon = when (dest) {
        Dest.Today -> Icons.Filled.Home
        Dest.DeepTime -> Icons.Filled.Timer
        Dest.Insights -> Icons.Filled.BarChart
        Dest.Me -> Icons.Filled.Person
    }
    Icon(icon, contentDescription = dest.label)
}

// "Me" tab combines Buddy + Settings behind a simple top-level toggle for Phase 1.
@Composable
private fun MeTabScreen() {
    var showSettings by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    androidx.compose.foundation.layout.Column {
        TabRow(selectedTabIndex = if (showSettings) 1 else 0) {
            Tab(selected = !showSettings, onClick = { showSettings = false }, text = { Text("Buddy") })
            Tab(selected = showSettings, onClick = { showSettings = true }, text = { Text("Settings") })
        }
        if (showSettings) SettingsScreen() else BuddyScreen()
    }
}
