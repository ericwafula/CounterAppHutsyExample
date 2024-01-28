package tech.ericwathome.counterapphutsyexample.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tech.ericwathome.counterapphutsyexample.R
import tech.ericwathome.counterapphutsyexample.ui.theme.CounterAppHutsyExampleTheme
import tech.ericwathome.counterapphutsyexample.presentation.util.ProgressProperties
import tech.ericwathome.counterapphutsyexample.ui.theme.green
import tech.ericwathome.counterapphutsyexample.ui.theme.light_brown

@Composable
fun ProgressTracker(
    modifier: Modifier = Modifier,
    progress: Float,
    color: Color,
    @DrawableRes emoji: Int,
    emojiSize: Dp
) {
    val cornerRadius = 20.dp
    val strokeWidth = 1.dp
    val cornerRadiusFloat = with(LocalDensity.current) { cornerRadius.toPx() }
    val strokeWidthFloat = with(LocalDensity.current) { strokeWidth.toPx() }
    val animateProgress = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = progress, ) {
        animateProgress.animateTo(progress)
    }

    Box(
        modifier = Modifier.height(160.dp)
    ) {
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(115.dp)
        ) {
            drawRoundRect(
                color = color,
                size = Size(
                    width = size.width * (animateProgress.value / 100),
                    height = size.height
                ),
                cornerRadius = CornerRadius(cornerRadiusFloat),
            )
            drawRoundRect(
                color = Color(0xFF827568),
                size = Size(
                    width = size.width * (animateProgress.value / 100),
                    height = size.height
                ),
                cornerRadius = CornerRadius(cornerRadiusFloat),
                style = Stroke(width = strokeWidthFloat)
            )
            drawRoundRect(
                color = Color(0xFF827568),
                size = Size(
                    width = size.width,
                    height = size.height
                ),
                cornerRadius = CornerRadius(cornerRadiusFloat),
                style = Stroke(width = strokeWidthFloat)
            )
        }
        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(emojiSize)
                .padding(top = 23.dp, end = 8.dp)
                .animateContentSize(),
            painter = painterResource(id = emoji),
            contentDescription = stringResource(id = R.string.emoji)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressTrackerPreview() {
    CounterAppHutsyExampleTheme {
        ProgressTracker(
            progress = 24f,
            color = light_brown,
            emoji = R.drawable.happy,
            emojiSize = 69.dp
        )
    }
}