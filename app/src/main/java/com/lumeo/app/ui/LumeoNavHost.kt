package com.lumeo.app.ui

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.lumeo.app.data.AppPreferences
import com.lumeo.app.data.UsageRepository
import com.lumeo.app.ui.screens.*

private sealed class Dest(val route: String, val label: String) {
    data object Today : Dest("today", "Today")
    data object Deep : Dest("deep", "Deep Time")
    data object Insights : Dest("insights", "Insights")
    data object Me : Dest("me", "Me")
}

@Composable
fun LumeoNavHost(
    activity: Activity,
    prefs: AppPreferences,
    usage: UsageRepository,
    onDarkModeChanged: (Boolean) -> Unit
) {
    val nav = rememberNavController()
    var refresh by remember { mutableIntStateOf(0) }
    val current by nav.currentBackStackEntryAsState()
    val currentDestination = current?.destination

    val destinations = listOf(
        Dest.Today,
        Dest.Deep,
        Dest.Insights,
        Dest.Me
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                destinations.forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == destination.route
                        } == true,
                        onClick = {
                            nav.navigate(destination.route) {
                                popUpTo(nav.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = when (destination) {
                                    Dest.Today -> Icons.Filled.Home
                                    Dest.Deep -> Icons.Filled.Timer
                                    Dest.Insights -> Icons.Filled.BarChart
                                    Dest.Me -> Icons.Filled.Person
                                },
                                contentDescription = destination.label
                            )
                        },
                        label = {
                            Text(destination.label)
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = Dest.Today.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Dest.Today.route) {
                TodayScreen(
                    activity = activity,
                    prefs = prefs,
                    usage = usage,
                    refresh = refresh
                )
            }

            composable(Dest.Deep.route) {
                DeepTimeScreen(


                DeepTimeScreen(
                    prefs = prefs,
                    onChanged = { refresh++ }
                )
            }

            composable(Dest.Insights.route) {
                InsightsScreen(
                    usage = usage,
                    refresh = refresh
                )
            }

            composable(Dest.Me.route) {
                MeScreen(
                    activity = activity,
                    prefs = prefs,
                    usage = usage,
                    onDarkModeChanged = onDarkModeChanged,
                    onChanged = { refresh++ }
                )
            }
        }
    }
}

@Composable
private fun MeScreen(
    activity: Activity,
    prefs: AppPreferences,
    usage: UsageRepository,
    onDarkModeChanged: (Boolean) -> Unit,
    onChanged: () -> Unit
) {
    var settings by remember { mutableIntStateOf(0) }

    Column {
        if (settings == 1) {
            SettingsScreen(
                activity = activity,
                prefs = prefs,
                usage = usage,
                onDarkModeChanged = onDarkModeChanged,
                onChanged = onChanged
            )
        } else {
            BuddyScreen(
                prefs = prefs,
                onOpenSettings = { settings = 1 }
            )
        }
    }
}
