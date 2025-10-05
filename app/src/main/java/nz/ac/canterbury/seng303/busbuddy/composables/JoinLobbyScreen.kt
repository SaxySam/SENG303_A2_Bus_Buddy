package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.busbuddy.viewmodels.JoinLobbyViewModel

@Composable
fun JoinLobby(
    // navController: NavController,
    // joinLobbyViewModel: JoinLobbyViewModel
) {
    val context = LocalContext.current
    val modifier = Modifier

    Scaffold(
    ) { innerPadding ->
        Box(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJoinLobbyScreen()
{
    JoinLobby()
}