package com.nguyennhatminh614.spotifycompose

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SpotifyComposeApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}
