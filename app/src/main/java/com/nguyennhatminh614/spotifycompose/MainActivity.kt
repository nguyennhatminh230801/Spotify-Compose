package com.nguyennhatminh614.spotifycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nguyennhatminh614.core.designsystem.theme.SpotifyComposeTheme
import com.nguyennhatminh614.spotifycompose.navigation.SpotifyComposeNavHost
import com.nguyennhatminh614.spotifycompose.navigation.rememberSpotifyComposeAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotifyComposeTheme {
                val appState = rememberSpotifyComposeAppState()
                SpotifyComposeNavHost(
                    appState = appState,
                )
            }
        }
    }
}
