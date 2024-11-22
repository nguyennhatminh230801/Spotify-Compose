package com.nguyennhatminh614.feature.player.route

import android.os.Build
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.model.Song
import com.nguyennhatminh614.core.navigation.NavigationEntryPoint
import com.nguyennhatminh614.core.navigation.navigateWithArgs
import com.nguyennhatminh614.feature.player.PlayerScreenRoute
import com.nguyennhatminh614.feature.player.PlayerViewModel

private const val KEY_SONG = "KEY_SONG"

fun NavController.navigateToPlayerScreens(
    song: Song,
    navOptions: NavOptions
) {
    navigateWithArgs(
        route = NavigationEntryPoint.PLAYER_SCREEN_ROUTE,
        args = bundleOf(
            KEY_SONG to song,
        ),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.playerScreenRoute(navController: NavHostController) {
    composable(route = NavigationEntryPoint.PLAYER_SCREEN_ROUTE) {
        val song = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            it.arguments?.getParcelable(KEY_SONG, Song::class.java)
        } else {
            it.arguments?.getParcelable(KEY_SONG) as? Song
        }

        if (song == null) {
            Toast.makeText(LocalContext.current, "Not song found", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
            return@composable
        }

        val viewModel: PlayerViewModel = hiltViewModel()
        viewModel.setSong(song)

        PlayerScreenRoute(
            viewModel = viewModel,
            navController = navController
        )
    }
}
