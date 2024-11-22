package com.example.usecase

import android.content.ContentUris
import android.net.Uri
import javax.inject.Inject

class GetLocalSongImagePathUseCase @Inject constructor() {
    companion object {
        private const val AUDIO_IMAGE_URI = "content://media/external/audio/albumart"
    }

    operator fun invoke(imageId: Long): Uri {
        return ContentUris.withAppendedId(Uri.parse(AUDIO_IMAGE_URI), imageId)
    }
}