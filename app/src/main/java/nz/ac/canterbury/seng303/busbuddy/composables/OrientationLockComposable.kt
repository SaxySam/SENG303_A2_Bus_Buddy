package nz.ac.canterbury.seng303.busbuddy.composables

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun LockOrientation(orientation: Int) {
    val activity = LocalContext.current.findActivity()
    DisposableEffect(orientation) {
        val previous = activity?.requestedOrientation ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        activity?.requestedOrientation = orientation
        onDispose {
            activity?.requestedOrientation = previous
        }
    }
}
