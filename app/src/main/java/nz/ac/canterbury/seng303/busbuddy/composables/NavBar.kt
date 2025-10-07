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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.ac.canterbury.seng303.busbuddy.ui.theme.*

@Composable
fun NavBar() {
    var selectedItem by remember { mutableIntStateOf(1) }

    val items = listOf(
        Triple(Icons.Filled.EmojiEvents, "Leaderboard", 0),
        Triple(Icons.Filled.Home, "Home", 1),
        Triple(Icons.Filled.Map, "Bus Routes", 2)
    )

    NavigationBar(
        containerColor = BlueGradientStart,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        tonalElevation = 12.dp
    ) {
        items.forEach { (icon, label, index) ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
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

@Preview
@Composable
fun PreviewNavBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        NavBar()
    }
}
