package nz.ac.canterbury.seng303.busbuddy

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import nz.ac.canterbury.seng303.busbuddy.composables.MainGameplay
import nz.ac.canterbury.seng303.busbuddy.composables.HomeScreen
import nz.ac.canterbury.seng303.busbuddy.composables.CameraScreen
import nz.ac.canterbury.seng303.busbuddy.composables.JoinLobby
import nz.ac.canterbury.seng303.busbuddy.composables.LockOrientation
import nz.ac.canterbury.seng303.busbuddy.composables.rememberSoundPool
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusBuddyTheme
import nz.ac.canterbury.seng303.busbuddy.viewmodels.JoinLobbyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusBuddyTheme() {
                val navController = rememberNavController()
                val joinLobbyViewModel: JoinLobbyViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = "HomeScreen") {
                        composable("HomeScreen") {
                            HomeScreen(navController, Modifier.padding(innerPadding))
                        }
                        composable("CameraScreen") {
                            CameraScreen()
                        }
                        composable("JoinLobbyScreen") {
                            JoinLobby(navController, joinLobbyViewModel)
                        }
                        composable("GamePlayScreen") {
                            LockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            MainGameplay(navController, Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}