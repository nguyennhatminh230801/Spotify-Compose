package com.example.usecase.repo.favoritesong.di

import com.example.repository.repo.favorite.FavoriteSongRepository
import com.example.usecase.repo.favoritesong.DeleteFavoriteSongUseCase
import com.example.usecase.repo.favoritesong.GetFavoriteSongStateByIDUseCase
import com.example.usecase.repo.favoritesong.InsertFavoriteSongUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoriteSongUseCaseModule {

    @Provides
    fun provideInsertFavoriteSongUseCase(
        favoriteSongRepository: FavoriteSongRepository,
    ): InsertFavoriteSongUseCase = InsertFavoriteSongUseCase(
        favoriteSongRepository
    )

    @Provides
    fun provideGetFavoriteSongStateByIDUseCase(
        favoriteSongRepository: FavoriteSongRepository,
    ): GetFavoriteSongStateByIDUseCase = GetFavoriteSongStateByIDUseCase(
        favoriteSongRepository
    )

    @Provides
    fun provideDeleteFavoriteSongUseCase(
        favoriteSongRepository: FavoriteSongRepository,
    ): DeleteFavoriteSongUseCase = DeleteFavoriteSongUseCase(
        favoriteSongRepository
    )
}