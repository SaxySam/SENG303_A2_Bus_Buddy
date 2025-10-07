package nz.ac.canterbury.seng303.busbuddy.composables

import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import nz.ac.canterbury.seng303.busbuddy.R

@Composable
fun rememberSoundPool(): Triple<SoundPool, Int, Int> {
    val context = LocalContext.current

    return remember {
        val soundPool = SoundPool.Builder().setMaxStreams(2).build()
        val coin = soundPool.load(context, R.raw.coin, 1)
        val crash = soundPool.load(context, R.raw.crash, 1)
        Triple(soundPool, coin, crash)
    }
}