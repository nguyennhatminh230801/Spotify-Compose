package com.nguyennhatminh614.feature.player.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayerScreenTimerBar(
    progress: Float,
    currentTime: String,
    remainingTime: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentTime,
                color = Color.White
            )

            Text(
                text = remainingTime,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 33, backgroundColor = 0xFF311B92)
@Composable
fun PreviewPlayerScreenTimerBar() {
    PlayerScreenTimerBar(
        progress = 0.2f,
        currentTime = "02:17",
        remainingTime = "02:17",
    )
}