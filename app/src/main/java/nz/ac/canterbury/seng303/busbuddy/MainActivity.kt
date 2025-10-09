package nz.ac.canterbury.seng303.busbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nz.ac.canterbury.seng303.busbuddy.composables.BusRoutesScreen
import nz.ac.canterbury.seng303.busbuddy.composables.HomeScreen
import nz.ac.canterbury.seng303.busbuddy.composables.NavBar
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusBuddyTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { NavBar(navController) }) { innerPadding ->
                    NavHost(navController = navController, startDestination = "HomeScreen") {
                        composable("HomeScreen") {
                            HomeScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable("BusRoutesScreen") {
                            BusRoutesScreen()
                        }
                    }
                }
            }
        }
    }
}