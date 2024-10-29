package com.nguyennhatminh614.feature.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nguyennhatminh614.core.designsystem.annotations.DarkLightPreview
import com.nguyennhatminh614.core.designsystem.theme.SpotifyComposeTheme
import com.nguyennhatminh614.core.designsystem.theme.customColorsPalette
import com.nguyennhatminh614.core.designsystem.theme.customFontStyle
import com.nguyennhatminh614.feature.player.components.PlayerScreenMusicController
import com.nguyennhatminh614.feature.player.components.PlayerScreenTimerBar
import com.nguyennhatminh614.feature.player.components.PlayerScreenTopAppBar

@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    var progress by remember { mutableFloatStateOf(0.2f) }
    var currentTime by remember { mutableStateOf("1:20") }
    var remainingTime by remember { mutableStateOf("2:30") }
    var author by remember { mutableStateOf("Author") }
    var songName by remember { mutableStateOf("SongName") }
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.customColorsPalette.backgroundColor
            )
            .padding(20.dp)
    ) {
        PlayerScreenTopAppBar(
            isFavorite = isFavorite,
            onBackPressed = { navController.popBackStack() },
            onFavoriteClick = { isFavorite = !isFavorite }
        )

        Spacer(modifier = Modifier.height(18.dp))

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.aspectRatio(1f)
                .fillMaxWidth()
                .padding(14.dp)
                .clip(RoundedCornerShape(36.dp))
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = songName,
            style = MaterialTheme.customFontStyle.textStyleNormal.copy(
                color = MaterialTheme.customColorsPalette.primaryTextColor,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = author,
            style = MaterialTheme.customFontStyle.textStyleNormal.copy(
                color = MaterialTheme.customColorsPalette.secondaryTextColor,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(40.dp))

        PlayerScreenTimerBar(
            progress = progress,
            currentTime = currentTime,
            remainingTime = remainingTime,
        )

        Spacer(modifier = Modifier.height(34.dp))

        PlayerScreenMusicController(
            modifier = Modifier
                .padding(bottom = 40.dp)
        )
    }
}

@DarkLightPreview
@Composable
private fun PreviewPlayerScreen() {
    SpotifyComposeTheme {
        PlayerScreen()
    }
}