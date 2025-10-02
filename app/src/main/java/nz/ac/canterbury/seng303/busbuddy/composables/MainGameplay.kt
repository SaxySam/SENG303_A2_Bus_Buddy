package nz.ac.canterbury.seng303.busbuddy.composables

import Road
import androidx.compose.foundation.layout.Box
import nz.ac.canterbury.seng303.busbuddy.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainGameplay(modifier: Modifier = Modifier) {
    val score = 30
    val nextStopMins = 4

    Box(modifier = modifier.fillMaxSize()) {
        Road(
            modifier = Modifier.fillMaxSize(),
            imageResId = R.drawable.road
        )
        Bus()
        HUD(
            score = score,
            nextStopMins = nextStopMins,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(12.dp)
        )
    }
}