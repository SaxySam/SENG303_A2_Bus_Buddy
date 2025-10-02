package nz.ac.canterbury.seng303.busbuddy.composables

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import nz.ac.canterbury.seng303.busbuddy.utils.SectionCard
import nz.ac.canterbury.seng303.busbuddy.utils.StyledButton

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = { NavBar() },
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
            IconButton(
                onClick = { },
                modifier = Modifier
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
                        StyledButton(
                            text = "Join Game",
                            icon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null) },
                            color = MaterialTheme.colorScheme.primary,
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp)
                        )

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
                        StyledButton(
                            text = "Bus Routes",
                            icon = { Icon(Icons.Filled.Map, contentDescription = null) },
                            color = MaterialTheme.colorScheme.primaryContainer,
                            onClick = { },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                            StyledButton(
                                text = "Top up",
                                icon = { Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null) },
                                color = MaterialTheme.colorScheme.tertiary,
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.data = "https://metrocard.metroinfo.co.nz/#/profile/topup".toUri()
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(20.dp)
                            )
                            StyledButton(
                                text = "Updates",
                                icon = { Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null) },
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.data = "https://go.metroinfo.co.nz/mtbp/en-gb/journey-planner/content/favorites".toUri()
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
