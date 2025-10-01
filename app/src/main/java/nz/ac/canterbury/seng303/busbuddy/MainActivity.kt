package nz.ac.canterbury.seng303.busbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import nz.ac.canterbury.seng303.busbuddy.composables.MainGameplay
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusBuddyTheme {
                MainGameplay(Modifier.fillMaxSize())
            }
        }
    }
}