package tech.ericwathome.counterapphutsyexample.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import tech.ericwathome.counterapphutsyexample.R

data class AppFonts(
    val cuteFont: FontFamily = FontFamily(
        Font(
            resId = R.font.cute_font
        )
    )
)

val LocalFont = compositionLocalOf { AppFonts() }