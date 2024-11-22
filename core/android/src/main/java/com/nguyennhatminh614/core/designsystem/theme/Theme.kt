package com.nguyennhatminh614.core.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.nguyennhatminh614.core.designsystem.model.CustomColorsPalette
import com.nguyennhatminh614.core.designsystem.model.CustomFontStyle

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val LightCustomColorsPalette = CustomColorsPalette(
    primaryColor = PrimaryColor,
    primaryTextColor = Color.Black,
    secondaryTextColor = Grey8E8E8E,
    backgroundColor = Color.White,
    trackColor = WhiteSmoke,
    iconDefaultColor = Color.Black
)

val DarkCustomColorsPalette = CustomColorsPalette(
    primaryColor = PrimaryColor,
    primaryTextColor = TextColorPrimary,
    secondaryTextColor = Grey8E8E8E,
    backgroundColor = DarkBackgroundColor,
    trackColor = WhiteSmoke,
    iconDefaultColor = Color.White
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

@Composable
fun SpotifyComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // "normal" palette, nothing change here
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val customColorsPalette  = when {
        darkTheme -> DarkCustomColorsPalette
        else -> LightCustomColorsPalette
    }

    val customFontStyle = CustomFontStyle(
        fontFamily = NunitoFontFamily
    )

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = customColorsPalette.primaryColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    // here is the important point, where you will expose custom objects
    CompositionLocalProvider(
        LocalCustomColorsPalette provides customColorsPalette,
        LocalTextStyles provides customFontStyle, // our custom palette
    ) {
        MaterialTheme(
            colorScheme = colorScheme , // the MaterialTheme still uses the "normal" palette
            content = content
        )
    }
}

val MaterialTheme.customColorsPalette: CustomColorsPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColorsPalette.current

val MaterialTheme.customFontStyle: CustomFontStyle
    @Composable
    @ReadOnlyComposable
    get() = LocalTextStyles.current