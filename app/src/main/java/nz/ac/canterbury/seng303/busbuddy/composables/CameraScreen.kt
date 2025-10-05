package nz.ac.canterbury.seng303.busbuddy.composables

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import nz.ac.canterbury.seng303.busbuddy.R
import nz.ac.canterbury.seng303.busbuddy.data.CameraState
import nz.ac.canterbury.seng303.busbuddy.utils.NoPermissionContent
import nz.ac.canterbury.seng303.busbuddy.utils.RequestType
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick
import nz.ac.canterbury.seng303.busbuddy.utils.rotateBitmap
import nz.ac.canterbury.seng303.busbuddy.viewmodels.CameraViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen() {
    val cameraPermissionState: PermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    MainCameraContentActivity(
        hasCameraPermission = cameraPermissionState.status.isGranted,
        onRequestCameraPermission = cameraPermissionState::launchPermissionRequest
    )
}


@Composable
fun MainCameraContentActivity(hasCameraPermission: Boolean, onRequestCameraPermission: () -> Unit) {

    if (hasCameraPermission) {
        CameraView()
    } else {
        NoPermissionContent(onRequestCameraPermission, RequestType.CAMERA)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraView(viewModel: CameraViewModel = koinViewModel()) {
    val cameraState: CameraState by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val navController = rememberNavController()
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scan QR") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                            contentDescription = "Back",
                        )
                    }
                }
            )
        },
        bottomBar = { NavBar() },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(ImageVector.vectorResource(R.drawable.ic_launcher_foreground), contentDescription = "Camera capture icon") },
                text = { Text("Take Photo") },
                onClick = { Toast.makeText(context, "Sent Toast", Toast.LENGTH_SHORT).show() },
                modifier = Modifier.bounceClick(),
            )
        }
    ) { paddingValues: PaddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                factory = { context ->
                    PreviewView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                        setBackgroundColor(android.graphics.Color.BLACK)
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                        scaleType = PreviewView.ScaleType.FILL_START
                    }.also { previewView ->
                        previewView.controller = cameraController
                        cameraController.bindToLifecycle(lifeCycleOwner)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview_CameraContent() {
    CameraView(koinViewModel())
}

