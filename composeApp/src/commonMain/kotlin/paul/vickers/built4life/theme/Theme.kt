package paul.vickers.built4life.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import paul.vickers.built4ife.theme.AppTypography
import paul.vickers.built4ife.theme.backgroundLight
import paul.vickers.built4ife.theme.errorContainerLight
import paul.vickers.built4ife.theme.errorLight
import paul.vickers.built4ife.theme.inverseOnSurfaceLight
import paul.vickers.built4ife.theme.inversePrimaryLight
import paul.vickers.built4ife.theme.inverseSurfaceLight
import paul.vickers.built4ife.theme.onBackgroundLight
import paul.vickers.built4ife.theme.onErrorContainerLight
import paul.vickers.built4ife.theme.onErrorLight
import paul.vickers.built4ife.theme.onPrimaryContainerLight
import paul.vickers.built4ife.theme.onPrimaryLight
import paul.vickers.built4ife.theme.onSecondaryContainerLight
import paul.vickers.built4ife.theme.onSecondaryLight
import paul.vickers.built4ife.theme.onSurfaceLight
import paul.vickers.built4ife.theme.onSurfaceVariantLight
import paul.vickers.built4ife.theme.onTertiaryContainerLight
import paul.vickers.built4ife.theme.onTertiaryLight
import paul.vickers.built4ife.theme.outlineLight
import paul.vickers.built4ife.theme.outlineVariantLight
import paul.vickers.built4ife.theme.primaryContainerLight
import paul.vickers.built4ife.theme.primaryLight
import paul.vickers.built4ife.theme.scrimLight
import paul.vickers.built4ife.theme.secondaryContainerLight
import paul.vickers.built4ife.theme.secondaryLight
import paul.vickers.built4ife.theme.surfaceBrightLight
import paul.vickers.built4ife.theme.surfaceContainerHighLight
import paul.vickers.built4ife.theme.surfaceContainerHighestLight
import paul.vickers.built4ife.theme.surfaceContainerLight
import paul.vickers.built4ife.theme.surfaceContainerLowLight
import paul.vickers.built4ife.theme.surfaceContainerLowestLight
import paul.vickers.built4ife.theme.surfaceDimLight
import paul.vickers.built4ife.theme.surfaceLight
import paul.vickers.built4ife.theme.surfaceVariantLight
import paul.vickers.built4ife.theme.tertiaryContainerLight
import paul.vickers.built4ife.theme.tertiaryLight

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

//private val darkScheme = darkColorScheme(
//    primary = primaryDark,
//    onPrimary = onPrimaryDark,
//    primaryContainer = primaryContainerDark,
//    onPrimaryContainer = onPrimaryContainerDark,
//    secondary = secondaryDark,
//    onSecondary = onSecondaryDark,
//    secondaryContainer = secondaryContainerDark,
//    onSecondaryContainer = onSecondaryContainerDark,
//    tertiary = tertiaryDark,
//    onTertiary = onTertiaryDark,
//    tertiaryContainer = tertiaryContainerDark,
//    onTertiaryContainer = onTertiaryContainerDark,
//    error = errorDark,
//    onError = onErrorDark,
//    errorContainer = errorContainerDark,
//    onErrorContainer = onErrorContainerDark,
//    background = backgroundDark,
//    onBackground = onBackgroundDark,
//    surface = surfaceDark,
//    onSurface = onSurfaceDark,
//    surfaceVariant = surfaceVariantDark,
//    onSurfaceVariant = onSurfaceVariantDark,
//    outline = outlineDark,
//    outlineVariant = outlineVariantDark,
//    scrim = scrimDark,
//    inverseSurface = inverseSurfaceDark,
//    inverseOnSurface = inverseOnSurfaceDark,
//    inversePrimary = inversePrimaryDark,
//    surfaceDim = surfaceDimDark,
//    surfaceBright = surfaceBrightDark,
//    surfaceContainerLowest = surfaceContainerLowestDark,
//    surfaceContainerLow = surfaceContainerLowDark,
//    surfaceContainer = surfaceContainerDark,
//    surfaceContainerHigh = surfaceContainerHighDark,
//    surfaceContainerHighest = surfaceContainerHighestDark,
//)
//
//private val mediumContrastLightColorScheme = lightColorScheme(
//    primary = primaryLightMediumContrast,
//    onPrimary = onPrimaryLightMediumContrast,
//    primaryContainer = primaryContainerLightMediumContrast,
//    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
//    secondary = secondaryLightMediumContrast,
//    onSecondary = onSecondaryLightMediumContrast,
//    secondaryContainer = secondaryContainerLightMediumContrast,
//    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
//    tertiary = tertiaryLightMediumContrast,
//    onTertiary = onTertiaryLightMediumContrast,
//    tertiaryContainer = tertiaryContainerLightMediumContrast,
//    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
//    error = errorLightMediumContrast,
//    onError = onErrorLightMediumContrast,
//    errorContainer = errorContainerLightMediumContrast,
//    onErrorContainer = onErrorContainerLightMediumContrast,
//    background = backgroundLightMediumContrast,
//    onBackground = onBackgroundLightMediumContrast,
//    surface = surfaceLightMediumContrast,
//    onSurface = onSurfaceLightMediumContrast,
//    surfaceVariant = surfaceVariantLightMediumContrast,
//    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
//    outline = outlineLightMediumContrast,
//    outlineVariant = outlineVariantLightMediumContrast,
//    scrim = scrimLightMediumContrast,
//    inverseSurface = inverseSurfaceLightMediumContrast,
//    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
//    inversePrimary = inversePrimaryLightMediumContrast,
//    surfaceDim = surfaceDimLightMediumContrast,
//    surfaceBright = surfaceBrightLightMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
//    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
//    surfaceContainer = surfaceContainerLightMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
//)
//
//private val highContrastLightColorScheme = lightColorScheme(
//    primary = primaryLightHighContrast,
//    onPrimary = onPrimaryLightHighContrast,
//    primaryContainer = primaryContainerLightHighContrast,
//    onPrimaryContainer = onPrimaryContainerLightHighContrast,
//    secondary = secondaryLightHighContrast,
//    onSecondary = onSecondaryLightHighContrast,
//    secondaryContainer = secondaryContainerLightHighContrast,
//    onSecondaryContainer = onSecondaryContainerLightHighContrast,
//    tertiary = tertiaryLightHighContrast,
//    onTertiary = onTertiaryLightHighContrast,
//    tertiaryContainer = tertiaryContainerLightHighContrast,
//    onTertiaryContainer = onTertiaryContainerLightHighContrast,
//    error = errorLightHighContrast,
//    onError = onErrorLightHighContrast,
//    errorContainer = errorContainerLightHighContrast,
//    onErrorContainer = onErrorContainerLightHighContrast,
//    background = backgroundLightHighContrast,
//    onBackground = onBackgroundLightHighContrast,
//    surface = surfaceLightHighContrast,
//    onSurface = onSurfaceLightHighContrast,
//    surfaceVariant = surfaceVariantLightHighContrast,
//    onSurfaceVariant = onSurfaceVariantLightHighContrast,
//    outline = outlineLightHighContrast,
//    outlineVariant = outlineVariantLightHighContrast,
//    scrim = scrimLightHighContrast,
//    inverseSurface = inverseSurfaceLightHighContrast,
//    inverseOnSurface = inverseOnSurfaceLightHighContrast,
//    inversePrimary = inversePrimaryLightHighContrast,
//    surfaceDim = surfaceDimLightHighContrast,
//    surfaceBright = surfaceBrightLightHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
//    surfaceContainerLow = surfaceContainerLowLightHighContrast,
//    surfaceContainer = surfaceContainerLightHighContrast,
//    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
//)

//private val mediumContrastDarkColorScheme = darkColorScheme(
//    primary = primaryDarkMediumContrast,
//    onPrimary = onPrimaryDarkMediumContrast,
//    primaryContainer = primaryContainerDarkMediumContrast,
//    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
//    secondary = secondaryDarkMediumContrast,
//    onSecondary = onSecondaryDarkMediumContrast,
//    secondaryContainer = secondaryContainerDarkMediumContrast,
//    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
//    tertiary = tertiaryDarkMediumContrast,
//    onTertiary = onTertiaryDarkMediumContrast,
//    tertiaryContainer = tertiaryContainerDarkMediumContrast,
//    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
//    error = errorDarkMediumContrast,
//    onError = onErrorDarkMediumContrast,
//    errorContainer = errorContainerDarkMediumContrast,
//    onErrorContainer = onErrorContainerDarkMediumContrast,
//    background = backgroundDarkMediumContrast,
//    onBackground = onBackgroundDarkMediumContrast,
//    surface = surfaceDarkMediumContrast,
//    onSurface = onSurfaceDarkMediumContrast,
//    surfaceVariant = surfaceVariantDarkMediumContrast,
//    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
//    outline = outlineDarkMediumContrast,
//    outlineVariant = outlineVariantDarkMediumContrast,
//    scrim = scrimDarkMediumContrast,
//    inverseSurface = inverseSurfaceDarkMediumContrast,
//    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
//    inversePrimary = inversePrimaryDarkMediumContrast,
//    surfaceDim = surfaceDimDarkMediumContrast,
//    surfaceBright = surfaceBrightDarkMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
//    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
//    surfaceContainer = surfaceContainerDarkMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
//)
//
//private val highContrastDarkColorScheme = darkColorScheme(
//    primary = primaryDarkHighContrast,
//    onPrimary = onPrimaryDarkHighContrast,
//    primaryContainer = primaryContainerDarkHighContrast,
//    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
//    secondary = secondaryDarkHighContrast,
//    onSecondary = onSecondaryDarkHighContrast,
//    secondaryContainer = secondaryContainerDarkHighContrast,
//    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
//    tertiary = tertiaryDarkHighContrast,
//    onTertiary = onTertiaryDarkHighContrast,
//    tertiaryContainer = tertiaryContainerDarkHighContrast,
//    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
//    error = errorDarkHighContrast,
//    onError = onErrorDarkHighContrast,
//    errorContainer = errorContainerDarkHighContrast,
//    onErrorContainer = onErrorContainerDarkHighContrast,
//    background = backgroundDarkHighContrast,
//    onBackground = onBackgroundDarkHighContrast,
//    surface = surfaceDarkHighContrast,
//    onSurface = onSurfaceDarkHighContrast,
//    surfaceVariant = surfaceVariantDarkHighContrast,
//    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
//    outline = outlineDarkHighContrast,
//    outlineVariant = outlineVariantDarkHighContrast,
//    scrim = scrimDarkHighContrast,
//    inverseSurface = inverseSurfaceDarkHighContrast,
//    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
//    inversePrimary = inversePrimaryDarkHighContrast,
//    surfaceDim = surfaceDimDarkHighContrast,
//    surfaceBright = surfaceBrightDarkHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
//    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
//    surfaceContainer = surfaceContainerDarkHighContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
//)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

val appShapes = Shapes(
    extraSmall = RoundedCornerShape(1.dp),
    small = RoundedCornerShape(2.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(6.dp),
    extraLarge = RoundedCornerShape(8.dp)
)

@Composable
fun AppTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = lightScheme,
        shapes = appShapes,
        typography = AppTypography,
        content = content
    )
}

