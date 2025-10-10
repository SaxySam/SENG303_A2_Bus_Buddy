package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import nz.ac.canterbury.seng303.busbuddy.R

@Composable
fun Bus(
    onBoundsChanged: (Rect) -> Unit = {},
    hitboxScale: Float = 0.85f
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp)
    ) {
        val painter = painterResource(R.drawable.bus)
        val aspect = run {
            val w = painter.intrinsicSize.width
            val h = painter.intrinsicSize.height
            if (w.isFinite() && h.isFinite() && h > 0f) w / h else 1f
        }

        Image(
            painter = painter,
            contentDescription = "Player Bus",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(maxWidth * 0.20f)
                .aspectRatio(aspect)
                .onGloballyPositioned { coords ->
                    val topLeft = coords.positionInRoot()
                    val size = coords.size.toSize()
                    val rect = Rect(topLeft, size)

                    val scaled = if (hitboxScale != 1f) {
                        val cx = (rect.left + rect.right) / 2f
                        val cy = (rect.top + rect.bottom) / 2f
                        val halfW = (rect.right - rect.left) / 2f * hitboxScale
                        val halfH = (rect.bottom - rect.top) / 2f * hitboxScale
                        Rect(
                            left = cx - halfW,
                            top = cy - halfH,
                            right = cx + halfW,
                            bottom = cy + halfH
                        )
                    } else rect
                    onBoundsChanged(scaled)
                },
            contentScale = ContentScale.FillBounds
        )
    }
}