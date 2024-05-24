package com.example.macropay.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.macropay.ui.theme.MicroPayChallengeTheme

@Composable
fun CircularProgressIndicatorFixMax(modifier: Modifier = Modifier) {
    Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(device = Devices.NEXUS_5, showBackground = true)
@Composable
fun CircularProgressIndicatorFixMaxPreview() {
    MicroPayChallengeTheme {
        CircularProgressIndicatorFixMax()
    }
}

