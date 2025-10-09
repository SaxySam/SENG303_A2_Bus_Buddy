package nz.ac.canterbury.seng303.busbuddy.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nz.ac.canterbury.seng303.busbuddy.viewmodels.RoutesViewModel

@Composable
fun RoutesDropDown(viewModel: RoutesViewModel) {
    val routes = listOf(
        "3 - Airport/Princess Margaret" to Color(0xFFE53935),
        "17 - Bryndwr/Huntsbury" to Color(0xFF1E88E5),
        "28 - Casebrook/Lyttelton" to Color(0xFF43A047),
        "80 - Lincoln/Burnside" to Color(0xFF8E24AA),
        "125 - Redwood/Westlake" to Color(0xFFFFB300),
        "Orbiter" to Color(0xFF009688)
    )

    var expanded by remember { mutableStateOf(false) }
    val selectedRouteName by viewModel.selectedRoute.collectAsState()
    val selectedRoute = routes.find { it.first == selectedRouteName } ?: routes.first()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Button(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(selectedRoute.second, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(selectedRoute.first)
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            routes.forEach { route ->
                DropdownMenuItem(
                    onClick = {
                        viewModel.selectRoute(route.first)
                        expanded = false
                    },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(route.second, shape = CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(route.first)
                        }
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.onPrimary,
                        leadingIconColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        }
    }
}

