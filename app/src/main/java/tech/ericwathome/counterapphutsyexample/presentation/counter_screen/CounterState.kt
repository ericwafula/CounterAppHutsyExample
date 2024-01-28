package tech.ericwathome.counterapphutsyexample.presentation.counter_screen

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tech.ericwathome.counterapphutsyexample.R
import tech.ericwathome.counterapphutsyexample.ui.theme.black
import tech.ericwathome.counterapphutsyexample.ui.theme.dark_brown
import tech.ericwathome.counterapphutsyexample.ui.theme.light_brown
import tech.ericwathome.counterapphutsyexample.ui.theme.red

data class CounterState(
    val progress: String = "0",
    val progressTextColor: Color = red,
    val textFieldValue: String = "",
    val isCountingDown: Boolean= false,
    val countDownButtonText: String = "!",
    val countDownButtonColor: Color = red,
    val countDownButtonTextColor: Color = Color.White,
    val subtractButtonColor: Color = black,
    val progressColor: Color = red,
    @DrawableRes val emoji: Int = R.drawable.crying,
    val emojiSize: Dp = 69.dp
)
