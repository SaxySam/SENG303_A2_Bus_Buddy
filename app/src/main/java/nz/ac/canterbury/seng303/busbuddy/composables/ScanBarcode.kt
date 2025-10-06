package nz.ac.canterbury.seng303.busbuddy.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.busbuddy.R
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanBarcode(
    onScanBarcode: suspend() -> Unit,
    barcodeValue: String?
) {
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scan QR or Barcode", color = Color.Black, textAlign = TextAlign.Center) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        bottomBar = { NavBar() }
    ) { paddingValues: PaddingValues ->
    Column(
        modifier = Modifier.fillMaxSize()
        .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Button(
            modifier = Modifier.fillMaxWidth(0.85f).bounceClick(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = {
                scope.launch { onScanBarcode() } }) {
            Text(
                text = "Scan Barcode",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium
            ) }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = barcodeValue?: "00000000",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
    }
} }