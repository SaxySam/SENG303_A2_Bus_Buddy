package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { NavBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // Profile icon at the top-right
            IconButton(
                onClick = { /* handle profile click */ },
                modifier = Modifier.align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "Profile"
                )
            }

            // Buttons in the center
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { /* Join Game */ }, modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Outlined.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Join Game")
                }

                Button(onClick = { /* Leaderboard */ }, modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Filled.EmojiEvents, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Leaderboard")
                }

                Button(onClick = { /* Bus Routes */ }, modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Filled.Map, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Bus Routes")
                }

                Button(onClick = { /* Top up Metro Card */ }, modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Top up Metro Card")
                }

                Button(onClick = { /* Route Updates */ }, modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Route Updates")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
