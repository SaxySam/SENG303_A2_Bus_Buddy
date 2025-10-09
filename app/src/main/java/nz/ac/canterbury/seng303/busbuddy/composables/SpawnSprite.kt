package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.random.Random

@Composable
fun SpawnSprite(
    running: Boolean,
    id: Long,
    sprite: Int,
    modifier: Modifier = Modifier,
    sizeDp: Int = 55,
    pixelsPerSecond: Float = 1000f,
    startAboveRatio: Float = 0.2f,
    onBoundsChanged: (Long, Rect) -> Unit = { _, _ -> }
    ) {
    val density = LocalDensity.current
    val sizePx = with(density) { sizeDp.dp.toPx() }
    var widthPx by remember { mutableFloatStateOf(0f) }
    var heightPx by remember { mutableFloatStateOf(0f) }

    // Pick a random X
    val xPxState = remember(widthPx) {
        val x = if (widthPx <= sizePx) 0f else Random.nextFloat() * (widthPx - sizePx)
        mutableStateOf(x)
    }
    val xPx = xPxState.value

    // sprite animation
    val yAnim = remember { Animatable(0f) }
    val startY = -startAboveRatio * heightPx - sizePx
    val travelPx = if (heightPx <= 0f) 1f else (heightPx - startY + 1f)
    LaunchedEffect(running, heightPx, sizePx, pixelsPerSecond) {
        if (heightPx > 0f) {
            if (running) {
                val remainingDistance = travelPx - yAnim.value
                val remainingDuration = ((remainingDistance / pixelsPerSecond) * 1000f).toInt().coerceAtLeast(1)
                yAnim.animateTo(
                    targetValue = travelPx,
                    animationSpec = tween(durationMillis = remainingDuration, easing = LinearEasing)
                )
            } else {
                yAnim.stop()
            }
        }
    }

    // For collision detection with bus
    LaunchedEffect(id, xPx, startY, sizePx) {
        snapshotFlow { yAnim.value }
            .map { y -> Rect(Offset(xPx, startY + y), Size(sizePx, sizePx)) }
            .distinctUntilChanged()
            .collectLatest { rect -> onBoundsChanged(id, rect) }
    }

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
                contentDescription = "Falling sprite",
                modifier = Modifier
                    .size(sizeDp.dp)
                    .graphicsLayer {
                        translationX = xPx
                        translationY = startY + yAnim.value
                    }
            )
        }
    }
}