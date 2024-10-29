package com.nguyennhatminh614.core.designsystem.model

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Immutable
import com.nguyennhatminh614.core.designsystem.theme.WhiteSmoke

/**
 * References: Android - Create custom colors in Compose with Material 3.
 * Link: "https://stackoverflow.com/a/77041136"
 */
@Immutable
data class CustomColorsPalette(
    //Default color
    val primaryColor: Color = Color.Unspecified,

    //Text color
    val primaryTextColor: Color = Color.Unspecified,
    val secondaryTextColor: Color = Color.Unspecified,

    //Background color
    val backgroundColor: Color = Color.Unspecified,

    //Others color
    val trackColor: Color = Color.Unspecified,
    val iconDefaultColor: Color = Color.Unspecified,
)


