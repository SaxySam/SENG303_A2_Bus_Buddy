import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import nz.ac.canterbury.seng303.busbuddy.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun Road(
    running: Boolean,
    modifier: Modifier = Modifier,
) {
    val imageResId: Int = R.drawable.road
    val pixelsPerSecond = 1000f
    val density = LocalDensity.current
    var containerHeightPx by remember { mutableFloatStateOf(0f) }
    var lastPhase by remember { mutableStateOf(0f) }

    // Animate moving road
    val overlap = 2f
    val distance = if (containerHeightPx <= 0f) 1f else containerHeightPx
    val phase by if (running) {
        val anim = rememberInfiniteTransition()
            .animateFloat(
                initialValue = 0f,
                targetValue = distance,
                animationSpec = infiniteRepeatable(
                    tween(((distance / pixelsPerSecond) * 1000f).toInt(), easing = LinearEasing)
                )
            )
        derivedStateOf {
            lastPhase = anim.value
            anim.value
        }
    } else {
        rememberUpdatedState(lastPhase)
    }

    Box(
        modifier = modifier.onSizeChanged { size ->
            containerHeightPx = with(density) { size.height.toFloat() }
        }
    ) {
        // First tile
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { translationY = phase },
            contentScale = ContentScale.FillBounds
        )
        // Second tile
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { translationY = phase - distance + overlap},
            contentScale = ContentScale.FillBounds
        )
    }
}