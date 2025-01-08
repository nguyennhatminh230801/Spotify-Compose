package com.example.usecase.repo.favoritesong

import com.example.model.FavoriteSong
import com.example.repository.repo.favorite.FavoriteSongRepository
import javax.inject.Inject

class InsertFavoriteSongUseCase @Inject constructor(
    private val favoriteSongRepository: FavoriteSongRepository,
) {
    suspend operator fun invoke(songFavorite: FavoriteSong) =
        favoriteSongRepository.insertFavoriteSong(songFavorite)
}