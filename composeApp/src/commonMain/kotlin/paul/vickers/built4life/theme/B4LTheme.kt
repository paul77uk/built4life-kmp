package paul.vickers.built4ife.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val LightColorTheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    error = Error,
    background = Background,
    onBackground = OnBackground,
    surface = Surface,
    onSurface = OnSurface,
    outline = Outline,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceContainerLow = SurfaceLowest,
    surfaceContainerHigh = SurfaceHighestContainer,
)

val shapes = Shapes(
    extraSmall = RoundedCornerShape(12.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(28.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

//val DarkColorTheme = darkColorScheme(
//    primary = PrimaryDark,
//    onPrimary = OnPrimary,
//    secondary = Secondary,
//    onSecondary = OnSecondary,
//    error = Error,
//    background = Background,
//    onBackground = OnBackground,
//    surface = Surface,
//    onSurface = OnSurface,
//    outline = Outline,
//    surfaceVariant = SurfaceVariant,
//    onSurfaceVariant = OnSurfaceVariant,
//    surfaceContainerLow = SurfaceLowest,
//)

@Composable
fun B4LTheme(
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = LightColorTheme,
        shapes = shapes,
        content = content
    )

}