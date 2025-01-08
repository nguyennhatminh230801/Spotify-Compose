package com.nguyennhatminh614.feature.player

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.model.Song
import com.nguyennhatminh614.core.designsystem.annotations.DarkLightPreview
import com.nguyennhatminh614.core.designsystem.theme.SpotifyComposeTheme
import com.nguyennhatminh614.core.designsystem.theme.customColorsPalette
import com.nguyennhatminh614.core.designsystem.theme.customFontStyle
import com.nguyennhatminh614.feature.player.components.PlayerScreenMusicController
import com.nguyennhatminh614.feature.player.components.PlayerScreenTimerBar
import com.nguyennhatminh614.feature.player.components.PlayerScreenTopAppBar

@Composable
fun PlayerScreenRoute(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: PlayerViewModel = hiltViewModel(),
) {
    var progress by remember { mutableFloatStateOf(0f) }
    var currentTime by remember { mutableStateOf("0:00") }
    var remainingTime by remember { mutableStateOf("9:99") }

    val isFavorite: Boolean by viewModel.isFavorite.collectAsStateWithLifecycle()

    val song: Song? by viewModel.song.collectAsStateWithLifecycle()

    if(song != null) {
        PlayerScreen(
            isFavorite = isFavorite,
            song = song!!,
            progress = progress,
            currentTime = currentTime,
            remainingTime = remainingTime,
            onBackPress = { navController.popBackStack() },
            onFavoriteClick = { viewModel.toggleFavorite() },
            modifier = modifier
        )
    }
}

@Composable
internal fun PlayerScreen(
    isFavorite: Boolean,
    song: Song,
    progress: Float,
    currentTime: String,
    remainingTime: String,
    onBackPress: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
            onBackPressed = onBackPress,
            onFavoriteClick = onFavoriteClick
        )

        Spacer(modifier = Modifier.height(18.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(song.imagePath)
                .memoryCachePolicy(CachePolicy.READ_ONLY)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
                .padding(14.dp)
                .clip(RoundedCornerShape(36.dp))
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = song.name,
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
            text = song.author,
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
        PlayerScreen(
            progress = 0.2f,
            isFavorite = false,
            song = Song(
                id = 0,
                path = "",
                duration = 0,
                name = "",
                imagePath = Uri.EMPTY,
                author = ""
            ),
            currentTime = "1:20",
            remainingTime = "2:30",
            onBackPress = {},
            onFavoriteClick = {},
        )
    }
}