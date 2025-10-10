package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.DirectionsBusFilled
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute1
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute3
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute5
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute7
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute8
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRouteOrbiter
import nz.ac.canterbury.seng303.busbuddy.utils.BarcodeScanner
import nz.ac.canterbury.seng303.busbuddy.utils.StyledButton
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick
import nz.ac.canterbury.seng303.busbuddy.viewmodels.JoinLobbyViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JoinLobby(
    navController: NavController,
    joinLobbyViewModel: JoinLobbyViewModel,
    onScanBarcode: suspend() -> Unit,
    barcodeValue: String?
) {

    val scope = rememberCoroutineScope()

    Scaffold(
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
            // Profile Button
            IconButton(
                onClick = { },
                modifier = Modifier
                    .bounceClick()
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .shadow(8.dp, RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(50))
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            // Back Button
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .bounceClick()
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Back Button",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .align(Alignment.Center)
                    .size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                // 'OR' Text
                Text(
                    "OR",
                    fontSize = 54.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    softWrap = false
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 80.dp),
                verticalArrangement = Arrangement.spacedBy(160.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Scan QR Button
                    StyledButton(
                        text = "Scan QR",
                        icon = { Icon(
                            Icons.Outlined.CameraAlt, contentDescription = null, modifier = Modifier.size(32.dp)) },
                        color = MaterialTheme.colorScheme.primary,
                        onClick = { scope.launch { onScanBarcode() } },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        verticalPadding = 32.dp,
                        fontSize = 32.sp
                    )
                    Text(
                        text = "QR Status: " + (barcodeValue?: "00000000"),
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Bus Route Buttons
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        maxItemsInEachRow = 2,
                    ) {
                        StyledButton(
                            text = "Orbiter",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = BusRouteOrbiter,
                            onClick = { joinLobbyViewModel.updateShowDialog(true) },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "1",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = BusRoute1,
                            onClick = { joinLobbyViewModel.updateShowDialog(true) },
                           modifier = Modifier
                               .padding(4.dp)
                               .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "3",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = BusRoute3,
                            onClick = { joinLobbyViewModel.updateShowDialog(true) },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "5",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = BusRoute5,
                            onClick = { joinLobbyViewModel.updateShowDialog(true) },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "7",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = BusRoute7,
                            onClick = { joinLobbyViewModel.updateShowDialog(true) },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "8",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = BusRoute8,
                            onClick = { joinLobbyViewModel.updateShowDialog(true) },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )
                    }
                }
            }

            if (joinLobbyViewModel.showDialog) {
                BusIdDialog(
                    onDismissRequest = {
                        joinLobbyViewModel.updateShowDialog(false)
                        joinLobbyViewModel.updateBusId("")
                    },
                    onConfirmation = {
                        joinLobbyViewModel.updateShowDialog(false)
                        joinLobbyViewModel.updateBusId("")
                        // TODO: Handle valid busId logic (take them to game if busId is true else toast message wrong busId)
                        navController.navigate("GamePlayScreen")
                    },
                    dialogTitle = "Enter Bus ID",
                    textValue = joinLobbyViewModel.busId,
                    onTextChange = { joinLobbyViewModel.updateBusId(it) },
                    icon = Icons.Outlined.DirectionsBusFilled
                )
            }
        }
    }
}

@Composable
fun BusIdDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    textValue: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = { Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp)) },
        title = { Text(text = dialogTitle) },
        text = {
            TextField(
                value = textValue,
                onValueChange = onTextChange,
                label = { Text("Bus ID") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        },
        containerColor = MaterialTheme.colorScheme.onPrimary,
        iconContentColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.primary
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewJoinLobbyScreen()
{
    val context = LocalContext.current
    val barcodeScanner = BarcodeScanner(context)
    val joinLobbyViewModel: JoinLobbyViewModel = viewModel()
    val barcodeResults = barcodeScanner.barCodeResults.collectAsStateWithLifecycle()
    JoinLobby(navController = rememberNavController(), joinLobbyViewModel, barcodeScanner::startScan, barcodeResults.value)
}