package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nz.ac.canterbury.seng303.busbuddy.utils.TextSwitch
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick
import nz.ac.canterbury.seng303.busbuddy.viewmodels.LeaderboardViewModel

@Composable
fun Leaderboard(
    navController: NavController,
    leaderboardViewModel: LeaderboardViewModel
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
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 80.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Trophy Icon
                    Icon(
                        modifier = Modifier
                            .size(54.dp),
                        imageVector = Icons.Filled.Leaderboard,
                        contentDescription = "Leaderboard",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    // Leaderboard Text
                    Text(
                        text = "Leaderboard",
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 54.sp
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextSwitch(
                        modifier = Modifier,
                        selectedIndex = leaderboardViewModel.textSwitchSelectedIndex,
                        items = leaderboardViewModel.textSwitchItems,
                        onSelectionChange = {
                            leaderboardViewModel.textSwitchSelectedIndex = it
                        }
                    )

                    if (leaderboardViewModel.textSwitchSelectedIndex == 1) {
                        leaderboardViewModel.expanded
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(leaderboardViewModel.selectedBusRoute.second ?: MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                                .clickable { leaderboardViewModel.expanded = !leaderboardViewModel.expanded }
                                .padding(12.dp)
                                .onGloballyPositioned { coordinates ->
                                    leaderboardViewModel.columnSize = coordinates.size.width
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = leaderboardViewModel.selectedBusRoute.first,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 18.sp
                            )
                            DropdownMenu(
                                modifier = Modifier
                                    .width(with(LocalDensity.current) { leaderboardViewModel.columnSize.toDp() }),
                                expanded = leaderboardViewModel.expanded,
                                onDismissRequest = { leaderboardViewModel.expanded = false },
                                containerColor = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                leaderboardViewModel.busRoutes.forEach { route ->
                                    DropdownMenuItem(
                                        modifier = Modifier
                                            .background(route.second),
                                        text = {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = route.first,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        },
                                        onClick = {
                                            leaderboardViewModel.selectedBusRoute = route
                                            leaderboardViewModel.expanded = false
                                        }
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLeaderboardScreen()
{
    val leaderboardViewModel: LeaderboardViewModel = viewModel()
    Leaderboard(navController = rememberNavController(), leaderboardViewModel)
}