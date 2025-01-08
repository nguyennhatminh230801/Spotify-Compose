package com.nguyennhatminh614.feature.player

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.model.FavoriteSong
import com.example.usecase.repo.favoritesong.DeleteFavoriteSongUseCase
import com.example.usecase.repo.favoritesong.GetFavoriteSongStateByIDUseCase
import com.example.usecase.repo.favoritesong.InsertFavoriteSongUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class PlayerViewModel @Inject constructor(
    //private val savedStateHandle: SavedStateHandle,
    private val insertFavoriteSongUseCase: InsertFavoriteSongUseCase,
    private val deleteFavoriteSongUseCase: DeleteFavoriteSongUseCase,
    private val getFavoriteSongStateByIDUseCase: GetFavoriteSongStateByIDUseCase,
) : ViewModel() {

    private val _song: MutableStateFlow<Song?> = MutableStateFlow(null)
    val song: StateFlow<Song?> = _song.asStateFlow()

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    init {
        _song
            .filterNotNull()
            .map {
                val isFavorite = getFavoriteSongStateByIDUseCase(it.id)
                Log.d("PlayerViewModel", "id: ${it.id}, isFavorite: $isFavorite")
                return@map isFavorite
            }
            .onEach { isFavorite ->
                _isFavorite.update { isFavorite }
            }
            .launchIn(viewModelScope)
    }

    fun setSong(song: Song) {
        _song.update { song }
    }

    fun toggleFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val newFavoriteState = isFavorite.value.not()

            //Update UI
            _isFavorite.update { newFavoriteState }

            //Update database privately
            if (newFavoriteState) {
                insertFavoriteSongUseCase(
                    FavoriteSong(
                        songId = song.value!!.id,
                    )
                )
            } else {
                deleteFavoriteSongUseCase(
                    FavoriteSong(
                        songId = song.value!!.id,
                    )
                )
            }
        }
    }

    companion object {
        const val KEY_PLAYER_SONG = "KEY_PLAYER_SONG"
    }
}