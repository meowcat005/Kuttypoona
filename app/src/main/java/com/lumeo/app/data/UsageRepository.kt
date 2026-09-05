package com.lumeo.app.data

import android.app.AppOpsManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

 data class AppUsage(val packageName: String, val label: String, val minutes: Int)
 data class UsageSnapshot(val totalMinutes: Int, val topApps: List<AppUsage>, val unlocks: Int, val weekly: List<Pair<String, Int>>, val yesterdayMinutes: Int)

class UsageRepository(private val context: Context) {
    private val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    private val pm = context.packageManager

    fun hasUsageAccess(): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        return appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), context.packageName) == AppOpsManager.MODE_ALLOWED
    }

    fun snapshot(): UsageSnapshot {
        if (!hasUsageAccess()) return UsageSnapshot(0, emptyList(), 0, emptyList(), 0)
        val todayStart = startOfDay(0)
        val now = System.currentTimeMillis()
        val stats = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, todayStart, now)
        val labelMap = HashMap<String, String>()
        val apps = stats.asSequence()
            .filter { it.totalTimeInForeground > 0 && it.packageName != context.packageName }
            .filter { isUserLaunchable(it.packageName) }
            .groupBy { it.packageName }
            .map { (pkg, list) ->
                val label = labelMap.getOrPut(pkg) { appLabel(pkg) }
                AppUsage(pkg, label, (list.sumOf { it.totalTimeInForeground } / 60000L).toInt())
            }
            .filter { it.minutes > 0 }
            .sortedByDescending { it.minutes }
        val total = apps.sumOf { it.minutes }
        return UsageSnapshot(total, apps.take(8), countUnlocks(todayStart, now), weekly(), minutesForDay(-1))
    }


    fun minutesForDay(offset: Int): Int {
        if (!hasUsageAccess()) return 0
        val s = startOfDay(offset); val e = if (offset == 0) System.currentTimeMillis() else startOfDay(offset + 1)
        return usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, s, e)
            .filter { it.packageName != context.packageName && isUserLaunchable(it.packageName) }
            .sumOf { it.totalTimeInForeground }
            .div(60000L).toInt()
    }

    private fun weekly(): List<Pair<String, Int>> {
        val out = mutableListOf<Pair<String, Int>>()
        val fmt = java.text.SimpleDateFormat("EEE", Locale.getDefault())
        for (i in 6 downTo 0) {
            val s = startOfDay(-i); val e = if (i == 0) System.currentTimeMillis() else startOfDay(-i + 1)
            val stats = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, s, e)
            val total = stats.filter { it.packageName != context.packageName && isUserLaunchable(it.packageName) }.sumOf { it.totalTimeInForeground } / 60000L
            out += fmt.format(java.util.Date(s)) to total.toInt()
        }
        return out
    }

    private fun countUnlocks(start: Long, end: Long): Int {
        val events = usm.queryEvents(start, end)
        val e = UsageEvents.Event(); var count = 0
        while (events.hasNextEvent()) { events.getNextEvent(e); if (e.eventType == UsageEvents.Event.SCREEN_INTERACTIVE) count++ }
        return count
    }

    private fun isUserLaunchable(pkg: String): Boolean = try { pm.getLaunchIntentForPackage(pkg) != null } catch (_: Exception) { false }
    private fun appLabel(pkg: String): String = try { pm.getApplicationLabel(pm.getApplicationInfo(pkg, 0)).toString() } catch (_: Exception) { pkg }
    private fun startOfDay(offset: Int): Long { val c = Calendar.getInstance(TimeZone.getDefault()); c.add(Calendar.DAY_OF_YEAR, offset); c.set(Calendar.HOUR_OF_DAY, 0); c.set(Calendar.MINUTE, 0); c.set(Calendar.SECOND, 0); c.set(Calendar.MILLISECOND, 0); return c.timeInMillis }
}
