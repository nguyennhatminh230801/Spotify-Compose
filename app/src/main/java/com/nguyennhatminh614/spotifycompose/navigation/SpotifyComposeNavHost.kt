package com.nguyennhatminh614.spotifycompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.nguyennhatminh614.core.navigation.NavigationEntryPoint
import com.nguyennhatminh614.feature.player.dummies.dummiesListSongRoute
import com.nguyennhatminh614.feature.player.route.playerScreenRoute

@Composable
fun SpotifyComposeNavHost(
    appState: SpotifyComposeAppState,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = appState.navController,
        startDestination = NavigationEntryPoint.DUMMIES_LIST_SONG_ROUTE,
        modifier = modifier,
    ) {
        dummiesListSongRoute(appState.navController)
        playerScreenRoute(appState.navController)
    }
}