package io.choizzy.compose.components.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.choizzy.pay_lib.R

@Immutable
data class ChoizzyTypography(
    val header: TextStyle,
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val caption: TextStyle
) {

    companion object {
        fun default() = ChoizzyTypography(
            header = TextStyle.Default,
            title1 = TextStyle.Default,
            title2 = TextStyle.Default,
            title3 = TextStyle.Default,
            body1 = TextStyle.Default,
            body2 = TextStyle.Default,
            caption = TextStyle.Default
        )
    }
}

val LocalTypography = staticCompositionLocalOf {
    ChoizzyTypography.default()
}

val AvenirFamily = FontFamily(
    Font(R.font.avenir_bold, weight = FontWeight.Bold),
    Font(R.font.avenir_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.avenir_demi, weight = FontWeight.SemiBold),
    Font(R.font.avenir_demi_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.avenir_heavy, weight = FontWeight.Black),
    Font(R.font.avenir_heavy_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(R.font.avenir_light, weight = FontWeight.Light),
    Font(R.font.avenir_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.avenir_medium, weight = FontWeight.Medium),
    Font(R.font.avenir_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.avenir_regular, weight = FontWeight.Normal),
    Font(R.font.avenir_thin, weight = FontWeight.Thin),
    Font(R.font.avenir_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
    Font(R.font.avenir_ultra_light, weight = FontWeight.ExtraLight),
    Font(R.font.avenir_ultra_light_it, weight = FontWeight.ExtraLight, style = FontStyle.Italic)
)

fun choizzyTypography(
    fontFamily: FontFamily
): ChoizzyTypography {
    val baseStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal
    )
    return ChoizzyTypography(
        header = baseStyle.copy(
            fontSize = 40.sp,
            lineHeight = 42.sp
        ),
        title1 = baseStyle.copy(
            fontSize = 32.sp,
            lineHeight = 35.sp
        ),
        title2 = baseStyle.copy(
            fontSize = 28.sp,
            lineHeight = 31.sp
        ),
        title3 = baseStyle.copy(
            fontSize = 24.sp,
            lineHeight = 27.sp
        ),
        body1 = baseStyle.copy(
            fontSize = 20.sp,
            lineHeight = 26.sp
        ),
        body2 = baseStyle.copy(
            fontSize = 16.sp,
            lineHeight = 22.sp
        ),
        caption = baseStyle.copy(
            fontSize = 13.sp,
            lineHeight = 16.sp
        )
    )
}