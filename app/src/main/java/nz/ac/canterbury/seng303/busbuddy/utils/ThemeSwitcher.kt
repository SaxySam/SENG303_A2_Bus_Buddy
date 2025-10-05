package nz.ac.canterbury.seng303.busbuddy.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import nz.ac.canterbury.seng303.busbuddy.R

@Composable
fun ThemeSwitcher(darkTheme: Boolean = false, onClick: () -> Unit, horizontalBias: Float = 0.85f, verticalBias: Float = 0.9f) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = { onClick(); callToast(context, darkTheme) },
            shape = CircleShape,
            modifier = Modifier.bounceClick()
                .align(BiasAlignment(horizontalBias, verticalBias))
        ) {
            if (darkTheme) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    modifier = Modifier.scale(1.25f),
                    contentDescription = "NightMode")

            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    modifier = Modifier.scale(1.25f),
                    contentDescription = "DayMode")
            }
        }
    }
}

fun callToast(context: Context, darkTheme: Boolean) {
    if (darkTheme) {
        Toast.makeText(context,
            "Light Mode",
            Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context,
            "Dark Mode",
            Toast.LENGTH_SHORT).show()
    }
}