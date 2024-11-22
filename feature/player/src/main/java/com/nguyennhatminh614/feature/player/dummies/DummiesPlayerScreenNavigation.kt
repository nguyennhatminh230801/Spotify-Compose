package com.nguyennhatminh614.feature.player.dummies

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nguyennhatminh614.core.navigation.NavigationEntryPoint

fun NavController.navigateToDummiesListSongs(navOptions: NavOptions) =
    navigate(route = NavigationEntryPoint.DUMMIES_LIST_SONG_ROUTE, navOptions)

fun NavGraphBuilder.dummiesListSongRoute(navController: NavHostController) {
    composable(route = NavigationEntryPoint.DUMMIES_LIST_SONG_ROUTE) {
        val viewModel: DummiesSongsViewModel = hiltViewModel()
        DummiesSongRoute(
            viewModel = viewModel,
            navController = navController
        )
    }
}
