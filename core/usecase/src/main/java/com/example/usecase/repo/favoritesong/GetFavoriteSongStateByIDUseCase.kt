package com.example.usecase.repo.favoritesong

import com.example.repository.repo.favorite.FavoriteSongRepository
import javax.inject.Inject

class GetFavoriteSongStateByIDUseCase @Inject constructor(
    private val favoriteSongRepository: FavoriteSongRepository,
){
    suspend operator fun invoke(songId: Long): Boolean = favoriteSongRepository.isSongFavorite(songId)
}
