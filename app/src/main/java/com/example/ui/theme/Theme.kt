package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = GoldAccent,
    onPrimary = OnGold,
    primaryContainer = EmeraldContainer,
    onPrimaryContainer = GoldLight,
    secondary = EmeraldLight,
    onSecondary = Color.White,
    secondaryContainer = SurfaceVariantDark,
    onSecondaryContainer = Color.White,
    tertiary = GoldLight,
    onTertiary = OnGold,
    background = BackgroundDark,
    onBackground = Color(0xFFF0EFEA),
    surface = SurfaceDark,
    onSurface = Color(0xFFEDEBE4),
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = Color(0xFFD6D1C4),
    outline = GoldDark,
    outlineVariant = Color(0xFF274336)
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE2F4EB),
    onPrimaryContainer = EmeraldDark,
    secondary = GoldDark,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFBF2D8),
    onSecondaryContainer = OnGold,
    tertiary = EmeraldLight,
    onTertiary = Color.White,
    background = BackgroundLight,
    onBackground = Color(0xFF1B1B1B),
    surface = SurfaceLight,
    onSurface = Color(0xFF1B1B1B),
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = Color(0xFF4C4639),
    outline = Color(0xFF8C7D65),
    outlineVariant = Color(0xFFDCD5C5)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
