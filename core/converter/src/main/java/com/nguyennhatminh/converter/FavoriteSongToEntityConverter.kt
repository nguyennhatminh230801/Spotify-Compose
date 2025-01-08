package com.nguyennhatminh.converter

import com.example.database.entities.FavoriteSongEntity
import com.example.model.FavoriteSong

class FavoriteSongToEntityConverter : IConverter<FavoriteSong, FavoriteSongEntity>  {
    override fun convert(source: FavoriteSong): FavoriteSongEntity = FavoriteSongEntity(songId = source.songId)
}