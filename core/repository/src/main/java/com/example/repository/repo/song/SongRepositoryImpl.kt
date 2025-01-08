package com.example.repository.repo.song

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.model.Song
import com.example.repository.di.annotations.coroutines.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : SongRepository {

    companion object {
        private const val SELECTION: String = MediaStore.Audio.Media.IS_MUSIC + " != 0 "
        private const val DEFAULT_STRING_VALUE = ""
        private const val DEFAULT_LONG_VALUE = 0L
        private const val AUDIO_IMAGE_URI = "content://media/external/audio/albumart"
    }

    override suspend fun getAllSongs(): List<Song> = withContext(coroutineDispatcher) {
        return@withContext buildList {
            val cursor: Cursor? = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, SELECTION, null, null, null
            )

            if (cursor == null) {
                return@withContext emptyList<Song>()
            }

            repeat(cursor.count) { index ->
                cursor.moveToPosition(index)

                with(cursor) {
                    val songId: Long = getLongOrElse(MediaStore.Audio.Media._ID)
                    val linkSong: String = getStringOrElse(MediaStore.Audio.Media.DATA)
                    val imageID: Long = getLongOrElse(MediaStore.Audio.Media.ALBUM_ID)
                    val nameSong: String = getStringOrElse(MediaStore.Audio.Media.TITLE)
                    val author: String = getStringOrElse(MediaStore.Audio.Media.ARTIST)
                    val duration: Long = getLongOrElse(MediaStore.Audio.Media.DURATION)

                    add(
                        Song(
                            id = songId,
                            path = linkSong,
                            imagePath = getSongImagePath(imageID),
                            duration = duration,
                            name = nameSong,
                            author = author
                        )
                    )
                }
            }

            //Cursor close at the end
            cursor.close()
        }
    }

    private fun Cursor.getStringOrElse(
        columnName: String, defaultValue: String = DEFAULT_STRING_VALUE
    ): String {
        return if (getColumnIndex(columnName) < 0) defaultValue else getString(
            getColumnIndexOrThrow(
                columnName
            )
        )
    }

    private fun Cursor.getLongOrElse(
        columnName: String, defaultValue: Long = DEFAULT_LONG_VALUE
    ): Long {
        return if (getColumnIndex(columnName) < 0) defaultValue else getLong(
            getColumnIndexOrThrow(
                columnName
            )
        )
    }

    private fun getSongImagePath(imageId: Long): Uri =
        ContentUris.withAppendedId(Uri.parse(AUDIO_IMAGE_URI), imageId)
}