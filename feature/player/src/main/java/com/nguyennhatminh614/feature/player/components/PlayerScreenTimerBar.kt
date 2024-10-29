package com.nguyennhatminh614.feature.player.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nguyennhatminh614.core.designsystem.annotations.DarkLightPreview
import com.nguyennhatminh614.core.designsystem.theme.SpotifyComposeTheme
import com.nguyennhatminh614.core.designsystem.theme.customColorsPalette
import com.nguyennhatminh614.core.designsystem.theme.customFontStyle

@Composable
internal fun PlayerScreenTimerBar(
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
                .clip(RoundedCornerShape(10.dp))
            ,
            color = MaterialTheme.customColorsPalette.primaryColor,
            trackColor = MaterialTheme.customColorsPalette.trackColor,

        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentTime,
                style = MaterialTheme.customFontStyle.textStyleSemiBold.copy(
                    fontSize = 12.sp,
                )
            )

            Text(
                text = remainingTime,
                style = MaterialTheme.customFontStyle.textStyleSemiBold.copy(
                    fontSize = 12.sp,
                )
            )
        }
    }
}

@DarkLightPreview
@Composable
private fun PreviewPlayerScreenTimerBar() {
    SpotifyComposeTheme {
        PlayerScreenTimerBar(
            progress = 0.8f,
            currentTime = "02:17",
            remainingTime = "02:17",
        )
    }
}