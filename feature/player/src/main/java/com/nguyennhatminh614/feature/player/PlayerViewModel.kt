package com.nguyennhatminh614.feature.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _song: MutableStateFlow<Song?> = MutableStateFlow(null)
    val song: StateFlow<Song?> = _song.asStateFlow()

    fun setSong(song: Song) {
        _song.update { song }
    }

    companion object {
        const val KEY_PLAYER_SONG = "KEY_PLAYER_SONG"
    }
}