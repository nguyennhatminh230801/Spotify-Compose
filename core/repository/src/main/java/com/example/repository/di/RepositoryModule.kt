package com.example.repository.di

import com.example.repository.repo.favorite.FavoriteSongRepository
import com.example.repository.repo.favorite.FavoriteSongRepositoryImpl
import com.example.repository.repo.song.SongRepository
import com.example.repository.repo.song.SongRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSongRepository(
        songRepositoryImpl: SongRepositoryImpl
    ): SongRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteSongRepository(
        favoriteSongRepositoryImpl: FavoriteSongRepositoryImpl
    ): FavoriteSongRepository
}