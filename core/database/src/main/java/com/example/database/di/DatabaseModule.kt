package com.example.database.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.database.SpotifyComposeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): SpotifyComposeDatabase {
        return Room.databaseBuilder(
            context, SpotifyComposeDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideSongFavoriteDao(database: SpotifyComposeDatabase) = database.songFavoriteDao()

    companion object {
        const val DATABASE_NAME = "spotify_compose_database"
    }
}