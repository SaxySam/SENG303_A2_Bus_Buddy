package nz.ac.canterbury.seng303.busbuddy.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class JoinLobbyViewModel: ViewModel() {
    var showDialog by mutableStateOf(false)
    private set

    fun updateShowDialog(isDialog: Boolean) {
        showDialog = isDialog
    }

    var busId by mutableStateOf("")

    fun updateBusId(newBusId: String) {
        busId = newBusId
    }
}