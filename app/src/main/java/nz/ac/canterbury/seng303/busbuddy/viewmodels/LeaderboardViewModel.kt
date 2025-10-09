package nz.ac.canterbury.seng303.busbuddy.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LeaderboardViewModel: ViewModel() {
    val textSwitchItems = listOf("Bus routes", "Individual")

    var textSwitchSelectedIndex by mutableIntStateOf(0)
}