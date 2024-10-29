package com.nguyennhatminh614.feature.player.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nguyennhatminh614.core.designsystem.R
import com.nguyennhatminh614.core.designsystem.annotations.DarkLightPreview
import com.nguyennhatminh614.core.designsystem.components.PlayButton
import com.nguyennhatminh614.core.designsystem.theme.SpotifyComposeTheme
import com.nguyennhatminh614.core.designsystem.theme.customColorsPalette

@Composable
internal fun PlayerScreenMusicController(
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
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(painter = painterResource(
            id = R.drawable.ic_shuffle,
        ),
            tint = MaterialTheme.customColorsPalette.iconDefaultColor,
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    onShuffle()
                })

        Icon(painter = painterResource(id = R.drawable.ic_skip),
            tint = MaterialTheme.customColorsPalette.iconDefaultColor,
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    onPrevious()
                })

        PlayButton(
            painter = painterResource(
                id = if (isPlaying) R.drawable.ic_pause
                else R.drawable.ic_play,
            ), onNextAction = if (isPlaying) onPauseSong else onPlayingSong,
        )

        Icon(painter = painterResource(id = R.drawable.ic_skip),
            tint = MaterialTheme.customColorsPalette.iconDefaultColor,
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .rotate(180f)
                .clickable {
                    onNext()
                })

        Icon(painter = painterResource(id = R.drawable.ic_repeat),
            tint = MaterialTheme.customColorsPalette.iconDefaultColor,
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    onRepeat()
                })
    }
}

@DarkLightPreview
@Composable
private fun PreviewPlayerScreenMusicController() {
    SpotifyComposeTheme {
        PlayerScreenMusicController()
    }
}