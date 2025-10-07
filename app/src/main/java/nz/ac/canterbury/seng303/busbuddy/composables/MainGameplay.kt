package nz.ac.canterbury.seng303.busbuddy.composables

import Road
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nz.ac.canterbury.seng303.busbuddy.models.BusStop
import nz.ac.canterbury.seng303.busbuddy.models.Player
import nz.ac.canterbury.seng303.busbuddy.models.Sprite
import nz.ac.canterbury.seng303.busbuddy.models.SpriteType
import nz.ac.canterbury.seng303.busbuddy.models.SpriteType.Companion.Sprites
import kotlin.random.Random

@Composable
fun MainGameplay(modifier: Modifier = Modifier) {
    val player = Player("John", 30)
    val nextStop = BusStop("Northlands Mall",4)
    val (soundPool, coin, crash) = rememberSoundPool()
    val sprites = remember { mutableStateListOf<Sprite>() }
    val spriteRects = remember { mutableStateMapOf<Long, Rect>() }
    var busRect by remember { mutableStateOf<Rect?>(null) }

    // Main game loop
    LaunchedEffect(Sprites) {
        while (true) {
            // Select random object to blit
            val type = Sprites.random(random = Random)
            sprites += Sprite(id = System.nanoTime(), type = type)
            if (sprites.size > 10) sprites.removeAt(0)
            delay(700L)
        }
    }

    fun handleCollision(spriteId: Long) {
        val sprite = sprites.firstOrNull { it.id == spriteId } ?: return
        if (sprite.type == SpriteType.Coin) {
            //todo: update current score value
            soundPool.play(coin, 1f, 1f, 1, 0, 1f)
        } else {
            //todo: handle game over
            soundPool.play(crash, 1f, 1f, 1, 0, 1f)
        }
        sprites.remove(sprite)
        spriteRects.remove(spriteId)
    }

    Box(modifier.fillMaxSize()) {
        Road(
            Modifier.fillMaxSize()
        )

        sprites.forEach { s ->
            key(s.id) {
                SpawnSprite(
                    id = s.id,
                    sprite = s.type.resId,
                    sizeDp = s.type.defaultSizeDp,
                    modifier = Modifier.fillMaxSize(),
                    onBoundsChanged = { id, rect ->
                        spriteRects[id] = rect
                        busRect?.let { bus ->
                            if (rect.overlaps(bus)) handleCollision(id)
                        }
                    }
                )
            }
        }

        Bus(onBoundsChanged = { rect -> busRect = rect })
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