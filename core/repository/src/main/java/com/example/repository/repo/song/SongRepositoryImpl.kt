package com.example.repository.repo.song

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.example.model.Song
import com.example.repository.di.annotations.coroutines.IoDispatcher
import com.example.usecase.GetLocalSongImagePathUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val getLocalSongImagePathUseCase: GetLocalSongImagePathUseCase,
) : SongRepository {

    companion object {
        private const val SELECTION: String = MediaStore.Audio.Media.IS_MUSIC + " != 0 "
        private const val DEFAULT_STRING_VALUE = ""
        private const val DEFAULT_LONG_VALUE = 0L
    }

    override suspend fun getAllSongs(): List<Song> = withContext(coroutineDispatcher) {
        return@withContext buildList {
            val cursor: Cursor? = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, SELECTION, null, null, null
            )

            while (cursor?.moveToNext() == true) {
                with(cursor) {
                    val linkSong: String =
                        if (getColumnIndex(MediaStore.Audio.Media.DATA) < 0) DEFAULT_STRING_VALUE else getString(
                            getColumnIndexOrThrow(
                                MediaStore.Audio.Media.DATA
                            )
                        )
                    val imageID: Long =
                        if (getColumnIndex(MediaStore.Audio.Media.ALBUM_ID) < 0) DEFAULT_LONG_VALUE else getLong(
                            getColumnIndexOrThrow(
                                MediaStore.Audio.Media.ALBUM_ID
                            )
                        )

                    val nameSong: String =
                        if (getColumnIndex(MediaStore.Audio.Media.TITLE) < 0) DEFAULT_STRING_VALUE else getString(
                            getColumnIndexOrThrow(
                                MediaStore.Audio.Media.TITLE
                            )
                        )
                    val author: String =
                        if (getColumnIndex(MediaStore.Audio.Media.ARTIST) < 0) DEFAULT_STRING_VALUE else getString(
                            getColumnIndexOrThrow(
                                MediaStore.Audio.Media.ARTIST
                            )
                        )
                    val duration: Long =
                        if (getColumnIndex(MediaStore.Audio.Media.DURATION) < 0) DEFAULT_LONG_VALUE else getLong(
                            getColumnIndexOrThrow(
                                MediaStore.Audio.Media.DURATION
                            )
                        )

                    add(
                        Song(
                            path = linkSong,
                            imagePath = getLocalSongImagePathUseCase(imageID),
                            duration = duration,
                            name = nameSong,
                            author = author
                        )
                    )
                }
            }

            //Cursor close at the end
            cursor?.close()
        }
    }
}