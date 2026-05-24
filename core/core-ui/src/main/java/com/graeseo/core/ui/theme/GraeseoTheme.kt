package com.graeseo.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Ink = Color(0xFF181614)
private val Ink3 = Color(0xFF6D6660)
private val Bg = Color(0xFFF7F4EF)
private val Surface = Color(0xFFFBF9F5)
private val Surface2 = Color(0xFFEFECE5)
private val Line = Color(0xFFE2DCD1)

private val GraeseoColorScheme = lightColorScheme(
    primary = Ink,
    onPrimary = Bg,
    primaryContainer = Surface2,
    onPrimaryContainer = Ink,
    secondary = Ink,
    onSecondary = Bg,
    secondaryContainer = Surface2,
    onSecondaryContainer = Ink,
    background = Bg,
    onBackground = Ink,
    surface = Surface,
    onSurface = Ink,
    surfaceVariant = Surface2,
    onSurfaceVariant = Ink3,
    surfaceContainer = Surface,
    surfaceContainerHigh = Surface2,
    outline = Line,
)

@Composable
fun GraeseoTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = GraeseoColorScheme,
        content = content,
    )
}
