package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nz.ac.canterbury.seng303.busbuddy.utils.TextSwitch
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick

@Composable
fun Leaderboard(
    navController: NavController
) {
    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
        ) {
            // Profile Button
            IconButton(
                onClick = { },
                modifier = Modifier
                    .bounceClick()
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .shadow(8.dp, RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(50))
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            // Back Button
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .bounceClick()
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Back Button",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLeaderboardScreen()
{
    Leaderboard(navController = rememberNavController())
}