package com.rodrigo.dextcg.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DexDarkColorScheme = darkColorScheme(
    primary = DexPrimary,
    onPrimary = DexOnPrimary,

    background = DexBackground,
    onBackground = DexText,

    surface = DexSurface,
    onSurface = DexText,

    surfaceVariant = DexSurfaceVariant,
    onSurfaceVariant = DexTextSecondary,

    primaryContainer = DexSurfaceVariant,
    onPrimaryContainer = DexPrimary,

    outline = DexOutline
)

@Composable
fun DexTCGTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DexDarkColorScheme,
        typography = Typography,
        content = content
    )
}