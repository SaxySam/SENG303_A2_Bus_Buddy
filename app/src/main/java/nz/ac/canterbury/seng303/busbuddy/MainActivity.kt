package nz.ac.canterbury.seng303.busbuddy

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nz.ac.canterbury.seng303.busbuddy.composables.BusRoutesScreen
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.common.util.concurrent.ListenableFuture
import nz.ac.canterbury.seng303.busbuddy.composables.AccountDrawer
import nz.ac.canterbury.seng303.busbuddy.composables.HomeScreen
import nz.ac.canterbury.seng303.busbuddy.utils.BarcodeScanner
import nz.ac.canterbury.seng303.busbuddy.composables.ScanBarcode
import nz.ac.canterbury.seng303.busbuddy.composables.JoinLobby
import nz.ac.canterbury.seng303.busbuddy.composables.LockOrientation
import nz.ac.canterbury.seng303.busbuddy.composables.MainGameplay
import nz.ac.canterbury.seng303.busbuddy.composables.NavBar
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusBuddyTheme
import nz.ac.canterbury.seng303.busbuddy.viewmodels.JoinLobbyViewModel

class MainActivity : ComponentActivity() {
    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var selectedItem by remember { mutableIntStateOf(1) }
            val joinLobbyViewModel: JoinLobbyViewModel = viewModel()
            val context = LocalContext.current
            var darkTheme by remember {mutableStateOf(false)}
            val barcodeScanner = BarcodeScanner(context)
            val barcodeResults = barcodeScanner.barCodeResults.collectAsStateWithLifecycle()

            BusBuddyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "HomeScreen",
                    ) {
                        composable("HomeScreen") {
                            HomeScreen(navController, darkTheme = darkTheme, onThemeUpdated = {darkTheme = !darkTheme})
                        }

                        composable("JoinLobbyScreen") {
                            JoinLobby(navController, joinLobbyViewModel, barcodeScanner::startScan, barcodeResults.value)
                        }

                        composable("QRCodeScanner") {
                            ScanBarcode(navController, barcodeScanner::startScan, barcodeResults.value)
                        }

                        composable("GamePlayScreen") {
                            LockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            MainGameplay(navController, Modifier.fillMaxSize())
                        }
                        composable("BusRoutesScreen") {
                            BusRoutesScreen(navController = navController, selectedItem = selectedItem)
                        }

                        composable("AccountDrawer") {
                            val accountDrawer = AccountDrawer(); accountDrawer.Screen(true, navController)
                        }
                        innerPadding
                        //todo: implement below composable navigation's
                        /**
                        composable("LeaderBoard") {
                            // LeaderBoardScreen()
                        }
                         */
                    }
                }
            }
        }
    }
}

// https://trello.com/b/qkzuaFiH/seng303-kanban