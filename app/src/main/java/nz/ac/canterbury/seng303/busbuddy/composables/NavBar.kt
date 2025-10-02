package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavBar() {
    var selectedItem by remember { mutableStateOf(1) } // Default "Home"

    val items = listOf(
        Triple(Icons.Filled.EmojiEvents, "Leaderboard", 0),
        Triple(Icons.Filled.Home, "Home", 1),
        Triple(Icons.Filled.Map, "Bus Routes", 2)
    )

    NavigationBar(
        containerColor = Color(0xFF4A90E2),
        contentColor = Color.White,
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
                        color = if (selectedItem == index) Color.White else Color.LightGray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFF50E3C2) // Highlight for selected item
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
            .background(color = Color.White)
    ) {
        NavBar()
    }
}
