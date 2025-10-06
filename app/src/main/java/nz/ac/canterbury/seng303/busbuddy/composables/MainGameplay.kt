package nz.ac.canterbury.seng303.busbuddy.composables

import Road
import SpawnSprite
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nz.ac.canterbury.seng303.busbuddy.models.BusStop
import nz.ac.canterbury.seng303.busbuddy.models.Player
import nz.ac.canterbury.seng303.busbuddy.models.Sprite
import nz.ac.canterbury.seng303.busbuddy.models.SpriteType.Companion.Sprites
import kotlin.random.Random

@Composable
fun MainGameplay(modifier: Modifier = Modifier) {
    val player = Player("John", 30)
    val nextStop = BusStop("Northlands Mall",4)
    val sprites = remember { mutableStateListOf<Sprite>() }

    // Main game loop
    LaunchedEffect(Sprites) {
        while (true) {
            val type = Sprites.random(random = Random)
            sprites += Sprite(id = System.nanoTime(), type = type)
            if (sprites.size > 10) sprites.removeAt(0)
            delay(700L)
        }
    }

    Box(modifier.fillMaxSize()) {
        Road(
            Modifier.fillMaxSize()
        )

        sprites.forEach { s ->
            key(s.id) {
                SpawnSprite(
                    modifier = Modifier.fillMaxSize(),
                    sprite = s.type.resId,
                    sizeDp = s.type.defaultSizeDp
                )
            }
        }

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