package com.example.repository.repo.favorite

import com.example.database.dao.SongFavoriteDao
import com.example.model.FavoriteSong
import com.example.repository.di.annotations.coroutines.IoDispatcher
import com.nguyennhatminh.converter.FavoriteSongToEntityConverter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteSongRepositoryImpl @Inject constructor(
    private val songFavoriteDao: SongFavoriteDao,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val favoriteSongToEntityConverter: FavoriteSongToEntityConverter,
) : FavoriteSongRepository {
    override suspend fun isSongFavorite(songId: Long): Boolean = withContext(coroutineDispatcher) {
        return@withContext songFavoriteDao.isSongFavorite(songId)
    }

    override suspend fun insertFavoriteSong(songFavorite: FavoriteSong) = withContext(coroutineDispatcher) {
        songFavoriteDao.insertFavoriteSong(favoriteSongToEntityConverter.convert(songFavorite))
    }

    override suspend fun deleteFavoriteSong(songFavorite: FavoriteSong) = withContext(coroutineDispatcher) {
        songFavoriteDao.deleteFavoriteSong(favoriteSongToEntityConverter.convert(songFavorite))
    }
}