package com.nguyennhatminh614.feature.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nguyennhatminh614.feature.player.components.PlayerScreenMusicController

@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Blue
            )
    ) {


        PlayerScreenMusicController(
            modifier = Modifier
                .padding(bottom = 40.dp)
        )
    }
}

@Preview
@Composable
fun PreviewPlayerScreen() {
    PlayerScreen()
}