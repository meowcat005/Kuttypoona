package com.lumeo.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

val Cream = Color(0xFFFDF8F0)
val ForestGreen = Color(0xFF2F4438)
val SageGreen = Color(0xFF8FA98B)
val Peach = Color(0xFFF4C9A8)
val WarmYellow = Color(0xFFF2C94C)
val MutedBrown = Color(0xFF6B5B4E)
val DarkGreenText = Color(0xFF1E2E24)

private val LumeoColorScheme = lightColorScheme(
    primary = ForestGreen,
    onPrimary = Cream,
    secondary = SageGreen,
    tertiary = Peach,
    background = Cream,
    surface = Cream,
    onBackground = DarkGreenText,
    onSurface = DarkGreenText,
)

val LumeoTypography = Typography(
    headlineMedium = TextStyle(fontSize = 28.sp),
    titleLarge = TextStyle(fontSize = 20.sp),
    bodyLarge = TextStyle(fontSize = 16.sp),
    bodyMedium = TextStyle(fontSize = 14.sp),
)

@Composable
fun LumeoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LumeoColorScheme,
        typography = LumeoTypography,
        content = content
    )
}
