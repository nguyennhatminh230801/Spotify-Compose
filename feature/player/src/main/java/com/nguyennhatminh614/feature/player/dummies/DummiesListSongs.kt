package com.nguyennhatminh614.feature.player.dummies

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.model.Song
import com.nguyennhatminh614.core.designsystem.annotations.DarkLightPreview
import com.nguyennhatminh614.core.designsystem.components.LoadingScreen
import com.nguyennhatminh614.core.designsystem.theme.SpotifyComposeTheme
import com.nguyennhatminh614.core.navigation.NavigationEntryPoint
import com.nguyennhatminh614.feature.player.route.navigateToPlayerScreens

@Composable
fun DummiesSongRoute(
    viewModel: DummiesSongsViewModel,
    navController: NavHostController,
) {
    var isPermissionGranted: Boolean? by rememberSaveable { mutableStateOf(null) }

    val songs by viewModel.songs.collectAsStateWithLifecycle()

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        Log.d("DummiesSongRoute", "isGranted: $isGranted")
        if (isPermissionGranted != isGranted) {
            isPermissionGranted = isGranted
        }
    }

    LaunchedEffect(Unit) {
        requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    LaunchedEffect(isPermissionGranted) {
        Log.d("DummiesSongRoute", "isPermissionGranted: $isPermissionGranted")

        if (isPermissionGranted == true) {
            viewModel.refreshSongs()
        }
    }

    if (isPermissionGranted == false) {
        RequestPermissionScreen(
            onRequestPermission = {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        )
    } else if (songs != null) {
        DummiesListSongs(
            songs = songs ?: emptyList(),
            onClick = { song ->
                val navOptions = navOptions {
                    popUpTo(
                        route = NavigationEntryPoint.DUMMIES_LIST_SONG_ROUTE,
                        popUpToBuilder = {
                            inclusive = true
                        }
                    )
                }

                navController.navigateToPlayerScreens(
                    song = song,
                    navOptions = navOptions
                )
            }
        )
    } else {
        LoadingScreen()
    }
}

@Composable
internal fun DummiesListSongs(
    songs: List<Song>,
    onClick: (Song) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.
            fillMaxSize()
    ) {
        items(songs) { song ->
            DummiesSong(
                song = song,
                onClick = onClick,
                modifier = modifier
            )

            HorizontalDivider()
        }
    }
}

@Composable
internal fun DummiesSong(
    song: Song,
    onClick: (Song) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .clickable { onClick(song) }
    ) {
        Text(text = "Name: ${song.name}")
        Text(text = "Author: ${song.author}")
        Text(text = "Song path: ${song.path}")
    }
}

@Composable
fun RequestPermissionScreen(
    modifier: Modifier = Modifier,
    onRequestPermission: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "You must provide permission to get all songs!!",
                modifier = Modifier.padding(bottom = 20.dp),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onRequestPermission
            ) {
                Text(text = "Request permission")
            }
        }
    }
}

@DarkLightPreview
@Composable
private fun PreviewRequestPermissionScreen() {
    SpotifyComposeTheme {
        RequestPermissionScreen()
    }
}

@DarkLightPreview
@Composable
private fun PreviewDummiesSongs(
    songs: List<Song> = SongsPreviewParameterProvider().values.toList(),
) {
    SpotifyComposeTheme {
        DummiesListSongs(
            songs = songs
        )
    }
}

class SongsPreviewParameterProvider : CollectionPreviewParameterProvider<Song>(
    listOf(
        Song(
            id = 0,
            name = "name1",
            author = "author1",
            imagePath = Uri.parse(""),
            path = "link1",
            duration = 0
        ),
        Song(
            id = 1,
            name = "name2",
            author = "author2",
            imagePath = Uri.parse(""),
            path = "link2",
            duration = 0
        )
    )
)

