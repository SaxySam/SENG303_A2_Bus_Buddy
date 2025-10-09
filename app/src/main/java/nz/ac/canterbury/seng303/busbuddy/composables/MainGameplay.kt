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
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import nz.ac.canterbury.seng303.busbuddy.models.BusStop
import nz.ac.canterbury.seng303.busbuddy.models.Player
import nz.ac.canterbury.seng303.busbuddy.models.Sprite
import nz.ac.canterbury.seng303.busbuddy.models.SpriteType
import nz.ac.canterbury.seng303.busbuddy.models.SpriteType.Companion.Sprites
import kotlin.random.Random

@Composable
fun MainGameplay(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var isGameOver by remember { mutableStateOf(false) }
    val running = !isGameOver
    val player = Player("John", 30)
    val nextStop = BusStop("Northlands Mall",4)
    val (soundPool, coin, crash, gameOver, background) = rememberSoundPool()
    val sprites = remember { mutableStateListOf<Sprite>() }
    val spriteRects = remember { mutableStateMapOf<Long, Rect>() }
    var busRect by remember { mutableStateOf<Rect?>(null) }
    var bgStreamId by remember { mutableStateOf(0) }

    // Main game loop
    LaunchedEffect(Sprites) {
        while (running) {
            // Select random object to blit
            val type = Sprites.random(random = Random)
            sprites += Sprite(id = System.nanoTime(), type = type)
            if (sprites.size > 10) sprites.removeAt(0)
            delay(700L)
        }
    }

    // background music
    LaunchedEffect(background) {
        var stream = 0
        while (stream == 0) {
            stream = soundPool.play(background, 1f, 1f, 1, -1, 1f)
            if (stream == 0) delay(50)
        }
        bgStreamId = stream
    }

    fun handleCollision(spriteId: Long) {
        val sprite = sprites.firstOrNull { it.id == spriteId } ?: return
        if (sprite.type == SpriteType.Coin) {
            //todo: update current score value
            soundPool.play(coin, 0.5f, 0.5f, 1, 0, 1f)
        } else {
            isGameOver = true
            if (bgStreamId != 0) {
                soundPool.stop(bgStreamId)
                bgStreamId = 0
            }
            soundPool.play(crash, 0.8f, 0.8f, 1, 0, 1f)
            soundPool.play(gameOver, 0.8f, 0.8f, 1, 0, 1f)
        }
        sprites.remove(sprite)
        spriteRects.remove(spriteId)
    }

    // load compatibles
    Box(modifier.fillMaxSize()) {
        Road(running = running, Modifier.fillMaxSize())
        sprites.forEach { s ->
            key(s.id) {
                SpawnSprite(
                    running = running,
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
            navController,
            running = running,
            player,
            nextStop,
            Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(12.dp)
        )
    }
}