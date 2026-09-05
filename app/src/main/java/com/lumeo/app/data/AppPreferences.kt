package com.lumeo.app.data

import android.content.Context

class AppPreferences(context: Context) {
    private val p = context.getSharedPreferences("kuttypoona", Context.MODE_PRIVATE)
    var goalMinutes: Int get() = p.getInt("goal", 240); set(v) = p.edit().putInt("goal", v.coerceIn(30, 1440)).apply()
    var showCompanion: Boolean get() = p.getBoolean("companion", true); set(v) = p.edit().putBoolean("companion", v).apply()
    var darkMode: Boolean get() = p.getBoolean("dark", false); set(v) = p.edit().putBoolean("dark", v).apply()
    var buddyName: String get() = p.getString("buddy_name", "Lumi") ?: "Lumi"; set(v) = p.edit().putString("buddy_name", v).apply()
    var xp: Int get() = p.getInt("xp", 120); set(v) = p.edit().putInt("xp", v).apply()
    var level: Int get() = p.getInt("level", 3); set(v) = p.edit().putInt("level", v).apply()
    var timerEndAt: Long get() = p.getLong("timer_end", 0L); set(v) = p.edit().putLong("timer_end", v).apply()
    var timerMinutes: Int get() = p.getInt("timer_minutes", 25); set(v) = p.edit().putInt("timer_minutes", v).apply()
    var timerRunning: Boolean get() = p.getBoolean("timer_running", false); set(v) = p.edit().putBoolean("timer_running", v).apply()
    var lastCompletedAt: Long get() = p.getLong("last_completed", 0L); set(v) = p.edit().putLong("last_completed", v).apply()
    var focusMinutes: Int get() = p.getInt("focus_minutes", 0); set(v) = p.edit().putInt("focus_minutes", v).apply()
    fun awardXp(amount: Int) {
        var x = xp + amount
        var l = level
        while (x >= 200) { x -= 200; l++ }
        xp = x; level = l
    }
}
