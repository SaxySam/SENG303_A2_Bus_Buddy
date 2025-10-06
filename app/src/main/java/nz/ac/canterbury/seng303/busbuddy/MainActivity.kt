package nz.ac.canterbury.seng303.busbuddy

import android.Manifest
import android.graphics.Camera
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.common.util.concurrent.ListenableFuture
import nz.ac.canterbury.seng303.busbuddy.composables.HomeScreen
import nz.ac.canterbury.seng303.busbuddy.composables.CameraScreen
import nz.ac.canterbury.seng303.busbuddy.composables.qrtesting.QRCodeScannerWithBottomSheet
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusBuddyTheme

class MainActivity : ComponentActivity() {
    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    // QR Test
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (!granted) {
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show()
            finish() } }
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        // QR Test
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        //
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // QR Test
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        //
        setContent {
            val context = LocalContext.current
            BusBuddyTheme() {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Navigation to be added later
                    NavHost(navController = navController, startDestination = "QRCodeScanner") {
                        composable("HomeScreen") {
                            HomeScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable("CameraScreen") {
                            CameraScreen()
                        }
                        composable("QRCodeScanner") {
                            QRCodeScannerWithBottomSheet()
                        }

                    }
                }
            }
        }
    }
}