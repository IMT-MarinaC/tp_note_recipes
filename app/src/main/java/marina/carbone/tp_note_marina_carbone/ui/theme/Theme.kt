package marina.carbone.tp_note_marina_carbone.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GreenDark,
    secondary = GreenDark,
    tertiary = GreenDark,

    //Other default colors to override
    background = Color(darkInt),
    surface = Color(0xFFFFFFF8),
    onPrimary = Color.Red,
    onSecondary = Color.Blue,
    onTertiary = Color.Green,
    onBackground = Color(greenDarkInt),
    onSurface = Color(0xFF1C1B1F),
)

private val LightColorScheme = lightColorScheme(
    primary = GreenDark,
    secondary = White,
    tertiary = Dark,

    background = Color(whiteInt),
    surface = Color(greenDarkInt),
    onPrimary = Color(whiteInt),
    onSecondary = Color(darkInt),
    onTertiary = Color(whiteInt),
    onBackground = Color(darkInt),
    onSurface = Color(whiteInt),

    )

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}