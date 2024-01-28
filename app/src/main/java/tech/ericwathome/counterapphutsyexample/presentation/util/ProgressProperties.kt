package tech.ericwathome.counterapphutsyexample.presentation.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tech.ericwathome.counterapphutsyexample.R
import tech.ericwathome.counterapphutsyexample.ui.theme.black
import tech.ericwathome.counterapphutsyexample.ui.theme.green
import tech.ericwathome.counterapphutsyexample.ui.theme.red

sealed class ProgressProperties(
    val color: Color,
    @DrawableRes val emoji: Int,
    val emojiSize: Dp,
    val textColor: Color
) {
    data object Low : ProgressProperties(
        color = red,
        emoji = R.drawable.crying,
        emojiSize = 69.dp,
        textColor = red
    )

    data object Medium : ProgressProperties(
        color = Color(0xFFF4D9AE),
        emoji = R.drawable.happy,
        emojiSize = 69.dp,
        textColor = black
    )

    data object Full : ProgressProperties(
        color = green,
        emoji = R.drawable.laughing,
        emojiSize = 160.dp,
        textColor = green
    )

    companion object {
        fun getProgressProperty(progress: Float): ProgressProperties {
            return when {
                progress < 20f -> Low
                progress in 20f..99f -> Medium
                else -> Full
            }
        }
    }
}