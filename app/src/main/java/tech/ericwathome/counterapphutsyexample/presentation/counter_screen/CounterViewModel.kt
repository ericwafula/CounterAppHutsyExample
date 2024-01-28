package tech.ericwathome.counterapphutsyexample.presentation.counter_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tech.ericwathome.counterapphutsyexample.R
import tech.ericwathome.counterapphutsyexample.presentation.util.ProgressProperties
import tech.ericwathome.counterapphutsyexample.ui.theme.dark_brown
import tech.ericwathome.counterapphutsyexample.ui.theme.green
import tech.ericwathome.counterapphutsyexample.ui.theme.light_brown
import tech.ericwathome.counterapphutsyexample.ui.theme.red

class CounterViewModel : ViewModel() {
    var state by mutableStateOf(CounterState())
        private set
    private var job: Job? = null

    fun onEvent(event: CounterEvent) {
        when (event) {
            CounterEvent.OnClickSubtract -> {
                if (state.progress.toInt() > 0) {
                    val counter = state.progress.toInt() - 1
                    val progressProperties =
                        ProgressProperties.getProgressProperty(counter.toFloat())

                    state = state.copy(
                        progress = counter.toString(),
                        progressColor = progressProperties.color,
                        emoji = progressProperties.emoji,
                        emojiSize = progressProperties.emojiSize,
                        progressTextColor = progressProperties.textColor,
                        countDownButtonText = if (counter == 0) "!" else "Start Countdown",
                        countDownButtonColor = if (counter == 0) red else light_brown,
                        countDownButtonTextColor = if (counter == 0) Color.White else dark_brown,
                        textFieldValue = ""
                    )
                }
            }

            CounterEvent.OnClickAdd -> {
                if (state.progress.toInt() < 100) {
                    val counter = state.progress.toInt() + 1
                    val progressProperties =
                        ProgressProperties.getProgressProperty(counter.toFloat())

                    state = state.copy(
                        progress = counter.toString(),
                        progressColor = progressProperties.color,
                        emoji = progressProperties.emoji,
                        emojiSize = progressProperties.emojiSize,
                        progressTextColor = progressProperties.textColor,
                        countDownButtonText = "Start Countdown",
                        countDownButtonColor = light_brown,
                        countDownButtonTextColor = dark_brown,
                        textFieldValue = ""
                    )
                }
            }

            is CounterEvent.OnEnterValue -> {
                state = state.copy(
                    textFieldValue = event.value
                )
            }

            CounterEvent.OnClickCountDown -> {
                if (state.isCountingDown) {
                    stopCountDown()
                } else {
                    initializeCountdown()
                }
            }

            CounterEvent.OnClickReset -> {
                val progressProperties = ProgressProperties.getProgressProperty(0f)

                state = state.copy(
                    progress = "0",
                    textFieldValue = "",
                    progressColor = progressProperties.color,
                    emoji = progressProperties.emoji,
                    emojiSize = 69.dp
                )
            }
        }
    }

    private fun initializeCountdown() {
        if (state.textFieldValue.isNotEmpty()) {
            val progress = state.textFieldValue.toFloat()

            state = state.copy(
                progress = if (progress > 100f) "100" else state.textFieldValue,
                textFieldValue = ""
            ).also {
                updateTextProgressEmoji(progress)
            }
        }

        state = state.copy(
            isCountingDown = true,
            countDownButtonText = "Pause",
            countDownButtonColor = light_brown,
            countDownButtonTextColor = dark_brown
        )

        job = viewModelScope.launch {
            var counter = state.progress.toInt()

            while (counter > 0) {
                delay(1000L)
                counter--
                state = state.copy(
                    progress = counter.toString()
                )

                if (counter < 20) {
                    updateTextProgressEmoji(counter.toFloat())
                } else {
                    updateTextProgressEmoji(counter.toFloat())
                }
            }

            if (counter == 0) state = state.copy(
                isCountingDown = false,
                countDownButtonText = "!",
                countDownButtonColor = red,
                countDownButtonTextColor = Color.White
            )
        }
    }

    private fun updateTextProgressEmoji(progress: Float) {
        val progressProperties = ProgressProperties.getProgressProperty(progress)

        state = state.copy(
            progressTextColor = progressProperties.textColor,
            progressColor = progressProperties.color,
            emoji = progressProperties.emoji
        )
    }

    private fun stopCountDown() {
        viewModelScope.launch {
            job?.cancel()

            state = if (state.progress.toInt() > 0) {
                val progressProperties =
                    ProgressProperties.getProgressProperty(state.progress.toFloat())

                state.copy(
                    countDownButtonText = "Resume",
                    isCountingDown = false,
                    progressTextColor = progressProperties.textColor,
                    progressColor = progressProperties.color,
                    emoji = progressProperties.emoji
                )
            } else {
                state.copy(
                    isCountingDown = false,
                    countDownButtonText = "!",
                    countDownButtonColor = red,
                    countDownButtonTextColor = Color.White
                )
            }
        }
    }
}