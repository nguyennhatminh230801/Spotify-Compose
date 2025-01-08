package com.example.repository.repo.favorite

import com.example.model.FavoriteSong

interface FavoriteSongRepository {

    suspend fun isSongFavorite(songId: Long): Boolean

    suspend fun insertFavoriteSong(songFavorite: FavoriteSong)

    suspend fun deleteFavoriteSong(songFavorite: FavoriteSong)
}