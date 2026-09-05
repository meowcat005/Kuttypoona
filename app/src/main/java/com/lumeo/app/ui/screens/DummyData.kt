package com.lumeo.app.ui.screens

// Phase 1 = fake data only. Phase 2 replaces this with real UsageStatsManager output.

data class AppUsageDummy(val appName: String, val minutes: Int)

val dummyTopApps = listOf(
    AppUsageDummy("Instagram", 88),
    AppUsageDummy("YouTube", 72),
    AppUsageDummy("WhatsApp", 52),
    AppUsageDummy("Chrome", 40),
)

val dummyWeek = listOf(
    "Mon" to 250, "Tue" to 232, "Wed" to 270, "Thu" to 204, "Fri" to 198
)

const val dummyTodayScreenTimeMin = 204   // 3h24m
const val dummyGoalMin = 240              // 4h00m
const val dummyFocusTimeMin = 125         // 2h05m
const val dummyUnlocks = 42
const val dummyYesterdayDeltaMin = 48     // "48 minutes less"

const val dummyBuddyName = "Lumi"
const val dummyBuddyLevel = 3
const val dummyBuddyXp = 120
const val dummyBuddyXpNeeded = 200

fun formatMinutes(min: Int): String {
    val h = min / 60
    val m = min % 60
    return if (h > 0) "${h}h ${m}m" else "${m}m"
}
