package com.lumeo.app.ui

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.lumeo.app.data.AppPreferences
import com.lumeo.app.data.UsageRepository
import com.lumeo.app.ui.screens.*

private sealed class Dest(val route: String, val label: String) { data object Today:Dest("today","Today"); data object Deep:Dest("deep","Deep Time"); data object Insights:Dest("insights","Insights"); data object Me:Dest("me","Me") }
private val dests=listOf(Dest.Today,Dest.Deep,Dest.Insights,Dest.Me)

@Composable fun LumeoNavHost(activity: Activity, prefs: AppPreferences, usage: UsageRepository, onDarkModeChanged: (Boolean) -> Unit) {
    val nav=rememberNavController()
    var refresh by remember { mutableIntStateOf(0) }
    Scaffold(bottomBar={ NavigationBar { val entry by nav.currentBackStackEntryAsState(); val cur=entry?.destination; dests.forEach { d ->
        NavigationBarItem(selected=cur?.hierarchy?.any{it.route==d.route}==true,onClick={nav.navigate(d.route){popUpTo(nav.graph.findStartDestination().id){saveState=true};launchSingleTop=true;restoreState=true}},icon={Icon(when(d){Dest.Today->Icons.Filled.Home;Dest.Deep->Icons.Filled.Timer;Dest.Insights->Icons.Filled.BarChart;Dest.Me->Icons.Filled.Person},d.label)},label={Text(d.label)})
    } } }) { pad ->
        NavHost(nav,startDestination=Dest.Today.route,modifier=Modifier.padding(pad)) {
            composable("today"){ TodayScreen(activity,prefs,usage,refresh) }
            composable("deep"){ DeepTimeScreen(prefs){ refresh++ } }
            composable("insights"){ InsightsScreen(usage,refresh) }
            composable("me"){ MeScreen(activity,prefs,usage,onDarkModeChanged){ refresh++ } }
        }
    }
}
@Composable private fun MeScreen(activity: Activity,prefs: AppPreferences,usage: UsageRepository,onDarkModeChanged:(Boolean)->Unit,onChanged:()->Unit){ var settings by remember{mutableStateOf(false)}; Column{ TabRow(selectedTabIndex=if(settings)1 else 0){Tab(!settings,{settings=false},{Text("Buddy")});Tab(settings,{settings=true},{Text("Settings")})}; if(settings) SettingsScreen(activity,prefs,usage,onDarkModeChanged,onChanged) else BuddyScreen(prefs) } }
