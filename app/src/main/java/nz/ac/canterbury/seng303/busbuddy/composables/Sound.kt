package nz.ac.canterbury.seng303.busbuddy.composables

import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import nz.ac.canterbury.seng303.busbuddy.R

data class SoundBank(
    val soundPool: SoundPool,
    val coin: Int,
    val crash: Int,
    val gameOver: Int,
    val background: Int
)

@Composable
fun rememberSoundPool(): SoundBank {
    val context = LocalContext.current

    return remember {
        val soundPool = SoundPool.Builder().setMaxStreams(2).build()
        val coin = soundPool.load(context, R.raw.coin, 1)
        val crash = soundPool.load(context, R.raw.crash, 1)
        val gameOver = soundPool.load(context, R.raw.game_over, 1)
        val background = soundPool.load(context, R.raw.background, 1)
        SoundBank(soundPool, coin, crash, gameOver, background)
    }
}