package nz.ac.canterbury.seng303.busbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nz.ac.canterbury.seng303.busbuddy.composables.MainGameplay
import nz.ac.canterbury.seng303.busbuddy.composables.HomeScreen
import nz.ac.canterbury.seng303.busbuddy.composables.CameraScreen
import nz.ac.canterbury.seng303.busbuddy.composables.JoinLobby
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusBuddyTheme
import nz.ac.canterbury.seng303.busbuddy.viewmodels.JoinLobbyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            BusBuddyTheme() {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val joinLobbyViewModel: JoinLobbyViewModel = viewModel()
                    NavHost(navController = navController, startDestination = "HomeScreen") {
                        composable("HomeScreen") {
                            HomeScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable("CameraScreen") {
                            CameraScreen()
                        }
                        composable("JoinLobbyScreen") {
                            JoinLobby(joinLobbyViewModel)
                        }
                        composable("GamePlayScreen") {
                            MainGameplay(Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}