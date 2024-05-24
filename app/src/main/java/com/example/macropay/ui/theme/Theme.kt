package com.example.macropay.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
        primary = BlueGrey500,
        primaryContainer = BlueGrey800,
        secondary = BlueGrey800,
        tertiary = Blue500,
        background = White800,
        surface = White800,
)

@Composable
fun MicroPayChallengeTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColorScheme, typography = Typography, content = content)
}