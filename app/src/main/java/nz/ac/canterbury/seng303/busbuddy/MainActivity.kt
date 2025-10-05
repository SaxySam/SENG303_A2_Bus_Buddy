package nz.ac.canterbury.seng303.busbuddy

import android.graphics.Camera
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import nz.ac.canterbury.seng303.busbuddy.composables.CameraScreen
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusBuddyTheme {
                //Testing Camera View
                CameraScreen()

//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    // Navigation to be added later
//                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
//}