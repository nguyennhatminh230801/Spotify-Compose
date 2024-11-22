package com.example.usecase.di

import com.example.usecase.GetLocalSongImagePathUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetLocalSongImagePathUseCase(): GetLocalSongImagePathUseCase {
        return GetLocalSongImagePathUseCase()
    }
}