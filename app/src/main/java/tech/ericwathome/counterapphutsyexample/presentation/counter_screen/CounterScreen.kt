package tech.ericwathome.counterapphutsyexample.presentation.counter_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.ericwathome.counterapphutsyexample.R
import tech.ericwathome.counterapphutsyexample.presentation.components.ProgressTracker
import tech.ericwathome.counterapphutsyexample.ui.theme.CounterAppHutsyExampleTheme
import tech.ericwathome.counterapphutsyexample.ui.theme.LocalFont

@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    CounterScreenContent(
        onEvent = viewModel::onEvent,
        state = viewModel.state
    )
}

@Composable
fun CounterScreenContent(
    onEvent: (CounterEvent) -> Unit,
    state: CounterState
) {
    val fontFamily = LocalFont.current.cuteFont

    BoxWithConstraints {
        val width = maxWidth

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 20.dp)
                .verticalScroll(
                    rememberScrollState()
                )
                .imePadding()
        ) {
            Spacer(modifier = Modifier.height(120.dp))
            ProgressTracker(
                progress = state.progress.toFloat(),
                color = state.progressColor,
                emoji = state.emoji,
                emojiSize = state.emojiSize
            )
            Spacer(modifier = Modifier.height(60.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { onEvent(CounterEvent.OnClickSubtract) }) {
                    Text(
                        text = "-",
                        color = state.subtractButtonColor,
                        fontSize = 40.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = state.progress,
                    color = state.progressTextColor,
                    fontSize = 170.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
                TextButton(onClick = { onEvent(CounterEvent.OnClickAdd) }) {
                    Text(
                        text = "+",
                        color = state.subtractButtonColor,
                        fontSize = 40.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.textFieldValue,
                onValueChange = {
                    onEvent(
                        CounterEvent.OnEnterValue(
                            it
                        )
                    )
                },
                shape = RoundedCornerShape(20.dp),
                placeholder = {
                    Text(text = stringResource(id = R.string.enter_value), fontSize = 14.sp)
                }
            )
            Spacer(modifier = Modifier.height(23.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .width(((width / 2) - 20.dp) - (27 / 2).dp)
                        .height(56.dp)
                        .background(
                            color = state.countDownButtonColor,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 1.dp,
                            color = Color(0xFFDE9E46),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable { onEvent(CounterEvent.OnClickCountDown) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.countDownButtonText,
                        color = state.countDownButtonTextColor,
                        fontSize = 14.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .width(((width / 2) - 20.dp) - (27 / 2).dp)
                        .height(56.dp)
                        .background(
                            color = Color(0xFF845400),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onEvent(CounterEvent.OnClickReset) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.reset),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CounterScreenPreview() {
    CounterAppHutsyExampleTheme {
        CounterScreenContent(
            onEvent = { },
            state = CounterState()
        )
    }
}