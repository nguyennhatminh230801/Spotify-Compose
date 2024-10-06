package com.nguyennhatminh614.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nguyennhatminh614.core.designsystem.theme.Blue0095E9

@Composable
fun BlueCircleButton(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    imageVector: ImageVector? = null,
    onNextAction: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = Blue0095E9,
                shape = RoundedCornerShape(50)
            )
    ) {
        if (imageVector != null) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .clickable { onNextAction() }
            )
        }

        if (painter != null) {
            Icon(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .clickable { onNextAction() }
            )
        }
    }
}

@Preview
@Composable
fun PreviewButtonBlueCircle() {
    BlueCircleButton(
        imageVector = Icons.Filled.PlayArrow,
        onNextAction = { /* no-op */ }
    )
}