package nz.ac.canterbury.seng303.busbuddy.composables

import Road
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nz.ac.canterbury.seng303.busbuddy.models.BusStop
import nz.ac.canterbury.seng303.busbuddy.models.Player

@Composable
fun MainGameplay(modifier: Modifier = Modifier) {
    val player = Player("John", 30)
    val nextStop = BusStop("Northlands Mall",4)

    Box(modifier.fillMaxSize()) {
        Road(
            Modifier.fillMaxSize()
        )
        Coin(
            modifier = Modifier.fillMaxSize()
        )
        Bus()
        HUD(
            player,
            nextStop,
            Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(12.dp)
        )
    }
}