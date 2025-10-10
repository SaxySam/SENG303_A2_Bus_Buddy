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
        "1" to Color(0xFF2BF1E1),
        "3" to Color(0xFF8358E8),
        "5" to Color(0xFFD9B716),
        "7" to Color(0xFFCB7212),
        "8" to Color(0xFFEE15C0),
        "Oc" to Color(0xFF399D23),
        "27" to Color(0xFFE16514),
        "29" to Color(0xFF07288A),
        "44" to Color(0xFF17BBA4),
        "60" to Color(0xFFE114D7),
        "80" to Color(0xFF8257AF),
        "95" to Color(0xFFE70F0F),
        "97" to Color(0xFFA274A8),
        "100" to Color(0xFF8B807C),
        "107" to Color(0xFF44A296),
        "120" to Color(0xFFF6A625),
        "125" to Color(0xFF5F983F),
        "130" to Color(0xFFA03A24),
        "135" to Color(0xFF16B04B),
        "140" to Color(0xFF10919D),
        "155" to Color(0xFF6B4194),
        "820" to Color(0xFF37B45C),
        "F" to Color(0xFF155992),
        "81" to Color(0xFFEB597D),
        "85" to Color(0xFFEF594B),
        "86" to Color(0xFFF1CD44),
        "91" to Color(0xFF108B45),
        "92" to Color(0xFF8F643F)
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
            containerColor = Color(0xDAFFFFFF),
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
                        textColor = MaterialTheme.colorScheme.primary,
                        leadingIconColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        }
    }
}

