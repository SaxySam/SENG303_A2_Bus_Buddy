import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun SpawnSprite(
    sprite: Int,
    modifier: Modifier = Modifier,
    sizeDp: Int = 55,
    pixelsPerSecond: Float = 1000f,
    startAboveRatio: Float = 0.2f
) {
    val density = LocalDensity.current
    val sizePx = with(density) { sizeDp.dp.toPx() }
    var widthPx by remember { mutableFloatStateOf(0f) }
    var heightPx by remember { mutableFloatStateOf(0f) }

    // Pick a random X location
    val xPxState = remember(widthPx) {
        val x = if (widthPx <= sizePx) 0f
        else Random.nextFloat() * (widthPx - sizePx)
        mutableStateOf(x)
    }
    val xPx = xPxState.value

    // Animate Y, matching the road
    val distance = if (heightPx <= 0f) 1f else heightPx
    val phaseY by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = distance,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = ((distance / pixelsPerSecond) * 1000f).toInt().coerceAtLeast(1),
                easing = LinearEasing
            )
        )
    )
    val startY = -startAboveRatio * heightPx - sizePx

    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { size ->
                widthPx = size.width.toFloat()
                heightPx = size.height.toFloat()
            },
        contentAlignment = Alignment.TopStart
    ) {
        if (widthPx > 0f && heightPx > 0f) {
            Image(
                painter = painterResource(sprite),
                contentDescription = "Coin",
                modifier = Modifier
                    .size(sizeDp.dp)
                    .graphicsLayer {
                        translationX = xPx
                        translationY = startY + phaseY
                    }
            )
        }
    }
}