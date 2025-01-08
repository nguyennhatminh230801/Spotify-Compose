package com.example.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteSongEntity(
    @PrimaryKey
    @ColumnInfo(name = "song_id")
    val songId: Long,
)
