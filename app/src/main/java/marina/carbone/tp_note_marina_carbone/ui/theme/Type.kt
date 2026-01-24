package marina.carbone.tp_note_marina_carbone.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import marina.carbone.tp_note_marina_carbone.R

val PlayfairDisplay = FontFamily(
    Font(R.font.playfairdisplay_regular, FontWeight.Normal),
    Font(R.font.playfairdisplay_italic, FontWeight.Normal, FontStyle.Italic),
)

val Oswald = FontFamily(
    Font(R.font.oswald_regular, FontWeight.Normal),
    Font(R.font.oswald_bold, FontWeight.Bold),
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        //lineHeight = 40.sp,
        letterSpacing = 2.sp
    ),

    titleMedium = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 20.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),

    labelSmall = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)