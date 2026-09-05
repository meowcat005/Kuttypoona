package com.lumeo.app.ui.screens
import com.lumeo.app.data.AppUsage
fun formatMinutes(min:Int):String { val h=min/60; val m=min%60; return if(h>0) "${h}h ${m}m" else "${m}m" }
