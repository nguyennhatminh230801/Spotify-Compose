package com.example.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Song(
    val id: Long,
    val path: String,
    val duration: Long,
    val name: String,
    val imagePath: Uri,
    val author: String,
): Parcelable