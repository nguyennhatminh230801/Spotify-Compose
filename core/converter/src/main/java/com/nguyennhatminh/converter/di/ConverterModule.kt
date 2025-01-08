package com.nguyennhatminh.converter.di

import com.nguyennhatminh.converter.FavoriteSongToEntityConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConverterModule {

    @Singleton
    @Provides
    fun providesFavoriteSongToEntityConverter() = FavoriteSongToEntityConverter()
}