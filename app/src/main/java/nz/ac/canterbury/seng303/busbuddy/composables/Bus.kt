package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nz.ac.canterbury.seng303.busbuddy.R

@Composable
fun Bus() {
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
            contentDescription = "Player bus",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(maxWidth * 0.20f)
                .aspectRatio(aspect),
            contentScale = ContentScale.FillBounds
        )
    }
}