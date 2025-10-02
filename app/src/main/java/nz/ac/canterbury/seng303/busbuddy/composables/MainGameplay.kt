package nz.ac.canterbury.seng303.busbuddy.composables

import Road
import androidx.compose.foundation.layout.Box
import nz.ac.canterbury.seng303.busbuddy.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainGameplay(modifier: Modifier = Modifier) {
    val score = 30

    Box(modifier = modifier.fillMaxSize()) {
        HUD(score = score)
        Road(modifier = Modifier.fillMaxSize(), imageResId = R.drawable.road)
        Bus()
    }
}