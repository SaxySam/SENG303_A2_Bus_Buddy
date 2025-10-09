package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.busbuddy.ui.theme.*


data class NavItem(
    val icon: ImageVector,
    val label: String,
    val index: Int,
    val route: String
)


@Composable
fun NavBar(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(1) }

    val items = listOf(
        NavItem(Icons.Filled.EmojiEvents, "Leaderboard", 0, "Leaderboard"),
        NavItem(Icons.Filled.Home, "Home", 1, "HomeScreen"),
        NavItem(Icons.Filled.Map, "Bus Routes", 2, "BusRoutesScreen")
    )

    NavigationBar(
        containerColor = BlueGradientStart,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        tonalElevation = 12.dp
    ) {
        items.forEach { (icon, label, index, route) ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(route)
                          },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(
                        text = label,
                        color = if (selectedItem == index) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = BlueGradientEnd
                )
            )
        }
    }
}
