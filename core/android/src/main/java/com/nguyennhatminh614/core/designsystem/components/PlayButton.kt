package com.nguyennhatminh614.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nguyennhatminh614.core.designsystem.R
import com.nguyennhatminh614.core.designsystem.theme.PrimaryColor

@Composable
fun PlayButton(
    painter: Painter,
    modifier: Modifier = Modifier,
    onNextAction: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = PrimaryColor,
                shape = RoundedCornerShape(50)
            )
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp)
                .clickable { onNextAction() }
        )
    }
}

@Preview
@Composable
private fun PreviewPlayButton() {
    PlayButton(
        painter = painterResource(id = R.drawable.ic_play),
        onNextAction = { /* no-op */ }
    )
}