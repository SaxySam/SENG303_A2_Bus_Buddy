import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun ZoomableImage(image: Painter) {
    var currentScale by remember { mutableFloatStateOf(1f) }
    var currentOffset by remember { mutableStateOf(Offset.Zero) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1280f / 959f)
    ) {
        val gestureState = rememberTransformableState { zoomFactor, panDelta, _ ->
            currentScale = (currentScale * zoomFactor).coerceIn(1f, 5f)

            val extraWidth = (currentScale - 1) * constraints.maxWidth
            val extraHeight = (currentScale - 1) * constraints.maxHeight

            val maxOffsetX = extraWidth / 2
            val maxOffsetY = extraHeight / 2

            currentOffset = Offset(
                x = (currentOffset.x + currentScale * panDelta.x).coerceIn(-maxOffsetX, maxOffsetX),
                y = (currentOffset.y + currentScale * panDelta.y).coerceIn(-maxOffsetY, maxOffsetY)
            )
        }

        Image(
            painter = image,
            contentDescription = "Zoomable Map",
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    scaleX = currentScale
                    scaleY = currentScale
                    translationX = currentOffset.x
                    translationY = currentOffset.y
                }
                .transformable(gestureState)
        )
    }
}