package nz.ac.canterbury.seng303.busbuddy

import android.graphics.Camera
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.common.util.concurrent.ListenableFuture
import nz.ac.canterbury.seng303.busbuddy.composables.HomeScreen
import nz.ac.canterbury.seng303.busbuddy.composables.CameraScreen
import nz.ac.canterbury.seng303.busbuddy.utils.BarcodeScanner
import nz.ac.canterbury.seng303.busbuddy.composables.ScanBarcode
import nz.ac.canterbury.seng303.busbuddy.composables.JoinLobby
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusBuddyTheme
import nz.ac.canterbury.seng303.busbuddy.viewmodels.JoinLobbyViewModel

class MainActivity : ComponentActivity() {
    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val barcodeScanner = BarcodeScanner(context)
            BusBuddyTheme() {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val joinLobbyViewModel: JoinLobbyViewModel = viewModel()
                    val barcodeResults = barcodeScanner.barCodeResults.collectAsStateWithLifecycle()

                    // Navigation to be added later
                    NavHost(navController = navController, startDestination = "QRCodeScanner") {
                        composable("HomeScreen") {
                            HomeScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable("CameraScreen") {
                            CameraScreen()
                        }
                        composable("JoinLobbyScreen") {
                            JoinLobby(joinLobbyViewModel)
                        }
                        composable("QRCodeScanner") {
                            ScanBarcode(barcodeScanner::startScan, barcodeResults.value)
                        }
                    }
                }
            }
        }
    }
}