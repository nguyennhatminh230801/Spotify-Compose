package com.nguyennhatminh614.feature.player.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nguyennhatminh614.core.designsystem.components.BlueCircleButton
import com.nguyennhatminh614.core.designsystem.R as DesignSystemR

@Composable
fun PlayerScreenMusicController(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = false,
    onShuffle: () -> Unit = {},
    onPrevious: () -> Unit = {},
    onNext: () -> Unit = {},
    onRepeat: () -> Unit = {},
    onPauseSong: () -> Unit = {},
    onPlayingSong: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = DesignSystemR.drawable.ic_shuffle),
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    onShuffle()
                }
        )

        Icon(
            painter = painterResource(id = DesignSystemR.drawable.ic_skip),
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    onPrevious()
                }
        )

        if (isPlaying) {
            BlueCircleButton(
                painter = painterResource(id = DesignSystemR.drawable.ic_pause),
            ) { onPauseSong() }
        } else {
            BlueCircleButton(
                imageVector = Icons.Filled.PlayArrow,
            ) { onPlayingSong() }
        }

        Icon(
            painter = painterResource(id = DesignSystemR.drawable.ic_skip),
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .rotate(180f)
                .clickable {
                    onNext()
                }
        )

        Icon(
            painter = painterResource(id = DesignSystemR.drawable.ic_repeat),
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    onRepeat()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerScreenMusicController() {
    PlayerScreenMusicController()
}