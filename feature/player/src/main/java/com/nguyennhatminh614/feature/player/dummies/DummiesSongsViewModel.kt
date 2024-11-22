package com.nguyennhatminh614.feature.player.dummies

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Song
import com.example.repository.repo.song.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DummiesSongsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val songRepository: SongRepository,
) : ViewModel() {
    private val _songs: MutableStateFlow<List<Song>?> = MutableStateFlow(null)
    val songs: StateFlow<List<Song>?> = _songs.asStateFlow()

    private val forceRefreshAction = MutableSharedFlow<Boolean?>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        replay = 1,
    )

    init {
        songs
            .onEach { Log.d(TAG, "List Songs: $it") }
            .launchIn(viewModelScope)
    }

    init {
        forceRefreshAction
            .onEach {
                Log.d(TAG, "forceRefreshAction: $it")
            }
            .filterNotNull()
            .flatMapLatest { isRefreshing ->
                flow {
                    if (isRefreshing) {
                        emit(songRepository.getAllSongs())
                    } else {
                        emit(null)
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .onEach { songs ->
                if (!songs.isNullOrEmpty()) {
                    Log.d(TAG, "refreshSongsFlow: get all songs complete")
                    _songs.update { songs }
                } else {
                    Log.d(TAG, "refreshSongsFlow: failed to fetch songs")
                }
            }
            .launchIn(scope = viewModelScope)
    }

    fun refreshSongs() {
        viewModelScope.launch {
            forceRefreshAction.emit(true)
        }
    }

    companion object {
        private const val TAG = "DummiesSongsViewModel"
    }
}