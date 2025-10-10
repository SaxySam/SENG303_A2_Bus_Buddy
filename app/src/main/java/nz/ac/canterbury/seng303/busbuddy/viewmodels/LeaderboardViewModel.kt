package nz.ac.canterbury.seng303.busbuddy.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import nz.ac.canterbury.seng303.busbuddy.models.Player
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute1
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute3
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute5
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute7
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRoute8
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BusRouteOrbiter

class LeaderboardViewModel: ViewModel() {
    val textSwitchItems = listOf("Bus routes", "Individual")

    var textSwitchSelectedIndex by mutableIntStateOf(0)

    val busRoutes = listOf("Orbiter" to BusRouteOrbiter, "1" to BusRoute1, "3" to BusRoute3, "5" to BusRoute5, "7" to BusRoute7, "8" to BusRoute8)

    var selectedBusRoute: Pair<String, Color?> by mutableStateOf("Select a bus route" to null)

    var expanded by mutableStateOf(false)

    var columnSize by mutableIntStateOf(0)

    val players = listOf(
        Player("John", 30),
        Player("Alice", 120),
        Player("Bob", 95),
        Player("Charlie", 110),
        Player("Diana", 130),
        Player("Ethan", 85),
        Player("Fiona", 140),
        Player("George", 100),
        Player("Hannah", 115),
        Player("Ian", 90)
    )
}