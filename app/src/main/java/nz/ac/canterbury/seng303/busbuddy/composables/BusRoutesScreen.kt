package nz.ac.canterbury.seng303.busbuddy.composables

import ZoomableImage
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import nz.ac.canterbury.seng303.busbuddy.R
import nz.ac.canterbury.seng303.busbuddy.utils.RoutesDropDown
import nz.ac.canterbury.seng303.busbuddy.utils.SectionCard
import nz.ac.canterbury.seng303.busbuddy.viewmodels.RoutesViewModel

@SuppressLint("DefaultLocale")
@Composable
fun BusRoutesScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel = remember { RoutesViewModel(context) }

    val isLoading by viewModel.loading.collectAsState()
    val scrollState = rememberScrollState()

    var nextStopName by remember { mutableStateOf("—") }
    var nextStopDistance by remember { mutableStateOf<Double?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val fine = results[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarse = results[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (fine || coarse) {
            findNextStop(context, viewModel) { stop, distance ->
                nextStopName = stop
                nextStopDistance = distance
            }
        } else {
            nextStopName = "Permission denied"
            nextStopDistance = null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar({ navController.navigate("AccountDrawer") }, navController) },
        bottomBar = { NavBar(navController) },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary
                )
            } else {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(scrollState)
                ) {
                    SectionCard(title = "Routes") {
                        RoutesDropDown(viewModel)
                        Spacer(Modifier.height(8.dp))

                        Button(
                            onClick = {
                                val fineGranted = ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                ) == PackageManager.PERMISSION_GRANTED
                                val coarseGranted = ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                ) == PackageManager.PERMISSION_GRANTED

                                if (fineGranted || coarseGranted) {
                                    findNextStop(context, viewModel) { stop, distance ->
                                        nextStopName = stop
                                        nextStopDistance = distance
                                    }
                                } else {
                                    permissionLauncher.launch(
                                        arrayOf(
                                            Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION
                                        )
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Find Next Stop")
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    SectionCard(title = "Next Stop") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = nextStopName,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            nextStopDistance?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = String.format("Distance: %.2f km", it / 1000),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    SectionCard(title = "Map") {
                        val image = painterResource(R.drawable.metro_map_christchurch)
                        ZoomableImage(image)
                    }

                    Spacer(Modifier.height(32.dp))
                }
            }
        }
    }
}

@RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
fun findNextStop(
    context: Context,
    viewModel: RoutesViewModel,
    updateText: (stopName: String, distanceMeters: Double?) -> Unit
) {
    val fusedLocation = LocationServices.getFusedLocationProviderClient(context)
    fusedLocation.lastLocation
        .addOnSuccessListener { location ->
            if (location != null) {
                val next = viewModel.getNextStop(location.latitude, location.longitude)
                if (next != null) {
                    val distance = viewModel.distanceMeters(
                        location.latitude, location.longitude,
                        next.lat, next.lon
                    )
                    updateText(next.name, distance)
                } else {
                    updateText("No nearby stops found", null)
                }
            } else {
                updateText("Unable to get location", null)
            }
        }
        .addOnFailureListener {
            updateText("Failed to get location", null)
        }
}
