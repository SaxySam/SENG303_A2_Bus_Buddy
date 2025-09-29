package nz.ac.canterbury.seng303.busbuddy.composables

import android.Manifest
import android.app.AlertDialog
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
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import nz.ac.canterbury.seng303.assignmentone.R
import nz.ac.canterbury.seng303.assignmentone.data.CameraState
import nz.ac.canterbury.seng303.assignmentone.utils.bounceClick
import nz.ac.canterbury.seng303.assignmentone.utils.rotateBitmap
import nz.ac.canterbury.seng303.assignmentone.viewmodels.CameraViewModel
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
        NoPermissionScreen(onRequestCameraPermission, RequestType.CAMERA)
    }
}

@Composable
fun CameraView(viewModel: CameraViewModel = koinViewModel()) {
    val cameraState: CameraState by viewModel.state.collectAsStateWithLifecycle()

    CameraContent(
        onPhotoCaptured = viewModel::storePhotoInGallery,
        lastCapturedPhoto = cameraState.capturedImage
    )
}

@Composable
fun CameraContent(onPhotoCaptured: (Bitmap) -> Unit, lastCapturedPhoto: Bitmap? = null) {

    val context = LocalContext.current
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    Scaffold(
        modifier = Modifier.fillMaxSize().background(Color.Black).padding(all = 8.dp),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(ImageVector.vectorResource(R.drawable.camera_shutter), contentDescription = "Camera capture icon") },
                text = { Text(context.getString(R.string.TakePhoto)) },
                onClick = { capturePhoto(context, cameraController, onPhotoCaptured) },
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

            if (lastCapturedPhoto != null) {
                LastPhotoPreview(
                    modifier = Modifier.align(BottomStart),
                    lastCapturedPhoto = lastCapturedPhoto
                )
            }
        }
    }
}

private fun capturePhoto(context: Context, cameraController: LifecycleCameraController, onPhotoCaptured: (Bitmap) -> Unit) {

    val mainExecutor = ContextCompat.getMainExecutor(context)

    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            //Save Image
            val correctedBitmap: Bitmap = image
            .toBitmap()
            .rotateBitmap(image.imageInfo.rotationDegrees)

            //Ask if user wants to save
            savePrompt(context, onPhotoCaptured, correctedBitmap)

            image.close()
        }
        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception) }
    })
}


private fun savePrompt(context: Context, onPhotoCaptured: (Bitmap) -> Unit, correctedBitmap: Bitmap) {
    Toast.makeText(context, context.getString(R.string.PhotoTaken), Toast.LENGTH_SHORT).show()
    val clearDataAlert = AlertDialog.Builder(context)
    clearDataAlert
        .setIcon((R.drawable.camera_shutter))
        .setTitle(R.string.CapturedImage)
//        .setView()
        .setMessage(context.getString(R.string.SavePhotoPrompt))
        .setCancelable(false)
        .setPositiveButton(context.getString(R.string.SavePhoto)) { dialog, id ->
            // Save Photo To Gallery
            onPhotoCaptured(correctedBitmap)
            Toast.makeText(
                context,
                context.getString(R.string.PhotoSaved),
                Toast.LENGTH_SHORT
            ).show()
        }
        .setNegativeButton(context.getString((R.string.Cancel))) { dialog, id ->
            // Dismiss the dialog
            dialog.dismiss()
        }
    val alert = clearDataAlert.create()
    alert.show()
}

@Composable
private fun LastPhotoPreview(
    modifier: Modifier = Modifier,
    lastCapturedPhoto: Bitmap
) {
    val capturedPhoto: ImageBitmap = remember(lastCapturedPhoto.hashCode()) { lastCapturedPhoto.asImageBitmap() }

    Card(
        modifier = modifier
            .size(192.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Image(
            bitmap = capturedPhoto,
            contentDescription = "Last captured photo",
            contentScale = ContentScale.Crop
        )
        Text("Test")
    }
}



@Preview
@Composable
private fun Preview_CameraContent() {
    CameraContent(
        onPhotoCaptured = {}
    )
}

