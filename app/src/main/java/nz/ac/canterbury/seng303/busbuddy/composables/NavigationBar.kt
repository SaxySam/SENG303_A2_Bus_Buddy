package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nz.ac.canterbury.seng303.busbuddy.data.NavItem
import nz.ac.canterbury.seng303.busbuddy.ui.theme.*
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick

@Composable
fun NavBar(
    navController: NavController,
//    selectedItem: Int,
//    onItemSelected: (Int) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(1) }
    val items = listOf(
        NavItem(Icons.Filled.EmojiEvents, "Leaderboard", 0, "LeaderBoard"),
        NavItem(Icons.Filled.Home, "Home", 1, "HomeScreen"),
        NavItem(Icons.Filled.Map, "Bus Routes", 2, "BusRoutesScreen")
    )

    NavigationBar(
        containerColor = BlueGradientStart,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        tonalElevation = 12.dp
    ) {
        items.forEach { (icon, label, index) ->
            NavigationBarItem(
                modifier = Modifier.bounceClick(),
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (selectedItem) {
                        0 -> navController.navigate("LeaderBoard")
                        1 -> navController.navigate("HomeScreen")
                        2 -> navController.navigate("BusRoutesScreen")
                    }
                },
                icon = { Icon(imageVector = icon, tint = Color.White, contentDescription = label) },
                label = {
                    Text(
                        text = label,
                        color = if (selectedItem == index)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = BlueGradientEnd
                )
            )
        }
    }
}
