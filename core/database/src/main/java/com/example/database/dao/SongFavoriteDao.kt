package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entities.FavoriteSongEntity

@Dao
interface SongFavoriteDao {
    @Query("SELECT EXISTS(SELECT 1 FROM FavoriteSongEntity WHERE song_id = :songId)")
    suspend fun isSongFavorite(songId: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteSong(songFavorite: FavoriteSongEntity)

    @Delete
    suspend fun deleteFavoriteSong(songFavorite: FavoriteSongEntity)
}