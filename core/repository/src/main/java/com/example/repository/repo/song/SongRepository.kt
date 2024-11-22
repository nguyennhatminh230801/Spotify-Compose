package com.example.repository.repo.song

import com.example.model.Song

interface SongRepository {
    suspend fun getAllSongs(): List<Song>
}