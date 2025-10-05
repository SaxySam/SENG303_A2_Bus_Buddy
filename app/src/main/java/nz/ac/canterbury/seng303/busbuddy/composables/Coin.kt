package nz.ac.canterbury.seng303.busbuddy.composables

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nz.ac.canterbury.seng303.busbuddy.R

@Composable
fun Coin(
    modifier: Modifier = Modifier,
    coinSizeDp: Int = 55,
    pixelsPerSecond: Float = 1000f,
    initialYRatio: Float = -0.2f  // start slightly above the screen
) {
    val coinResId: Int = R.drawable.coin
    val density = LocalDensity.current
    var containerHeightPx by remember { mutableFloatStateOf(0f) }
    val coinSizePx = with(density) { coinSizeDp.dp.toPx() }
    val distance = if (containerHeightPx <= 0f) 1f else containerHeightPx
    val transition = rememberInfiniteTransition()

    val phasePx by transition.animateFloat(
        initialValue = 0f,
        targetValue = distance,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = ((distance / pixelsPerSecond) * 1000f).toInt().coerceAtLeast(1),
                easing = LinearEasing
            )
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { size ->
                containerHeightPx = size.height.toFloat()
            }
    ) {
        if (containerHeightPx > 0f) {
            val startOffset = containerHeightPx * initialYRatio - coinSizePx

            Image(
                painter = painterResource(id = coinResId),
                contentDescription = "Moving Coin",
                modifier = Modifier
                    .size(coinSizeDp.dp)
                    .align(Alignment.TopCenter)
                    .graphicsLayer {
                        translationY = startOffset + phasePx
                    }
            )
        }
    }
}