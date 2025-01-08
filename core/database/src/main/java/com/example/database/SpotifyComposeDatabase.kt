package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.SongFavoriteDao
import com.example.database.entities.FavoriteSongEntity

@Database(
    entities = [FavoriteSongEntity::class],
    version = 1,
)
abstract class SpotifyComposeDatabase : RoomDatabase() {

    abstract fun songFavoriteDao(): SongFavoriteDao
}

