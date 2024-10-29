package com.nguyennhatminh614.feature.player.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nguyennhatminh614.core.designsystem.annotations.DarkLightPreview
import com.nguyennhatminh614.core.designsystem.theme.SpotifyComposeTheme
import com.nguyennhatminh614.core.designsystem.theme.customColorsPalette
import com.nguyennhatminh614.core.designsystem.theme.customFontStyle

@Composable
fun PlayerScreenTopAppBar(
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.customColorsPalette.iconDefaultColor,
            modifier = Modifier
                .padding(7.dp)
                .clickable(onClick = onBackPressed),
        )

        Text(
            text = "Now Playing",
            style = MaterialTheme.customFontStyle.textStyleNormal.copy(
                color = MaterialTheme.customColorsPalette.primaryTextColor,
                fontSize = 18.sp,
            ),
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )

        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else
                Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = MaterialTheme.customColorsPalette.iconDefaultColor,
            modifier = Modifier
                .padding(7.dp)
                .clickable(onClick = onFavoriteClick)
        )
    }
}

@DarkLightPreview
@Composable
private fun PreviewPlayerScreenTopAppBar() {
    SpotifyComposeTheme {
        PlayerScreenTopAppBar(isFavorite = true)
    }
}