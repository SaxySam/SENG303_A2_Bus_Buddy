package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.busbuddy.R
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanBarcode(
    navController: NavController,
    onScanBarcode: suspend() -> Unit,
    barcodeValue: String?
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Button(
            modifier = Modifier.fillMaxWidth(0.85f).bounceClick(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = {
                scope.launch { onScanBarcode() } }) {
            Text(
                text = context.getString(R.string.ScanQRBarcode),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium
            ) }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = barcodeValue?: context.getString(R.string.TempValue),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
    }
}