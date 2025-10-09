package nz.ac.canterbury.seng303.busbuddy.composables

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.busbuddy.data.NavigationItem
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BlueGradientStart
import nz.ac.canterbury.seng303.busbuddy.ui.theme.Typography
import nz.ac.canterbury.seng303.busbuddy.utils.SectionCard
import nz.ac.canterbury.seng303.busbuddy.utils.ShowToast
import nz.ac.canterbury.seng303.busbuddy.utils.StyledButton
import nz.ac.canterbury.seng303.busbuddy.utils.ThemeSwitcher
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick
import nz.ac.canterbury.seng303.busbuddy.utils.openURL
import org.koin.core.component.getScopeId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, darkTheme: Boolean, onThemeUpdated: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Scaffold(
        topBar = { val accountDrawer = AccountDrawer(); accountDrawer.Screen() },
        bottomBar = { NavBar(navController) },
        modifier = Modifier.fillMaxSize()
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
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionCard(title = "Game") {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                        // Join Game Button
                        StyledButton(
                            text = "Join Game",
                            icon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null, modifier = Modifier.size(35.dp)) },
                            color = MaterialTheme.colorScheme.primary,
                            onClick = { navController.navigate("JoinLobbyScreen") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            verticalPadding = 35.dp,
                            fontSize = 24.sp
                        )

                        // Leaderboard Button
                        StyledButton(
                            text = "Leaderboard",
                            icon = { Icon(Icons.Filled.EmojiEvents, contentDescription = null) },
                            color = MaterialTheme.colorScheme.secondary,
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp)
                        )
                    }
                }

                SectionCard(title = "Bus") {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                        // Bus Routes Button
                        StyledButton(
                            text = "Bus Routes",
                            icon = { Icon(Icons.Filled.Map, contentDescription = null) },
                            color = MaterialTheme.colorScheme.tertiary,
                            onClick = { },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                            // Card Top-Up Button
                            StyledButton(
                                text = "Top up",
                                icon = { Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null) },
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                onClick = {
                                    openURL(context, "https://metrocard.metroinfo.co.nz/#/profile/topup", ShowToast.HIDE)
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(20.dp)
                            )
                            // Metro Updates Button
                            StyledButton(
                                text = "Updates",
                                icon = { Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null) },
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                onClick = {
                                    openURL(context, "https://go.metroinfo.co.nz/mtbp/en-gb/journey-planner/content/favorites", ShowToast.HIDE)
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(20.dp)
                            )
                        }
                    }
                }
            }
        }

        // Dark Mode Button
        ThemeSwitcher(darkTheme = darkTheme, onClick = onThemeUpdated, 0.9f, 0.65f, 0.6f)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    var darkTheme = false
    HomeScreen(navController = rememberNavController(), darkTheme = darkTheme, onThemeUpdated = {darkTheme = !darkTheme})
}
