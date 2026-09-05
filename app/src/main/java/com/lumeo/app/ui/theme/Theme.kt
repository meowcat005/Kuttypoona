package com.lumeo.app.ui.theme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
val Cream=Color(0xFFFDF8F0);val ForestGreen=Color(0xFF2F4438);val SageGreen=Color(0xFF8FA98B);val Peach=Color(0xFFF4C9A8);val DarkGreenText=Color(0xFF1E2E24)
private val Light=lightColorScheme(primary=ForestGreen,onPrimary=Cream,secondary=SageGreen,tertiary=Peach,background=Cream,surface=Cream,onBackground=DarkGreenText,onSurface=DarkGreenText)
private val Dark=darkColorScheme(primary=SageGreen,secondary=Peach,background=Color(0xFF172019),surface=Color(0xFF1E2A22),onBackground=Cream,onSurface=Cream)
val LumeoTypography=Typography(headlineMedium=TextStyle(fontSize=28.sp),titleLarge=TextStyle(fontSize=20.sp),bodyLarge=TextStyle(fontSize=16.sp),bodyMedium=TextStyle(fontSize=14.sp))
@Composable fun LumeoTheme(darkMode:Boolean=false,content: @Composable () -> Unit){MaterialTheme(colorScheme=if(darkMode)Dark else Light,typography=LumeoTypography,content=content)}
