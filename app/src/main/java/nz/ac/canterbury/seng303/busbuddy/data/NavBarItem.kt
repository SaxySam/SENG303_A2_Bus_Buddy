package nz.ac.canterbury.seng303.busbuddy.data

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val icon: ImageVector,
    val label: String,
    val index: Int,
    val route: String
)
