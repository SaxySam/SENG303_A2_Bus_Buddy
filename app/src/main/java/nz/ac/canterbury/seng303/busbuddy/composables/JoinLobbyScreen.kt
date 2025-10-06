package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.DirectionsBusFilled
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nz.ac.canterbury.seng303.busbuddy.utils.StyledButton

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JoinLobby(
    // navController: NavController,
    // joinLobbyViewModel: JoinLobbyViewModel
) {
    // val context = LocalContext.current

    Scaffold(
        bottomBar = { NavBar() }
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
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 100.dp),
                verticalArrangement = Arrangement.spacedBy(100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                        .padding(horizontal = 16.dp, vertical = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StyledButton(
                        text = "Scan QR",
                        icon = { Icon(
                            Icons.Outlined.CameraAlt, contentDescription = null, modifier = Modifier.size(24.dp)) },
                        color = MaterialTheme.colorScheme.primary,
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        fontSize = 24.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                        .padding(horizontal = 16.dp, vertical = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxItemsInEachRow = 2,
                    ) {
                        StyledButton(
                            text = "Orbiter",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = MaterialTheme.colorScheme.primary,
                            onClick = { },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "1",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = MaterialTheme.colorScheme.primary,
                            onClick = { },
                           modifier = Modifier
                               .padding(4.dp)
                               .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "3",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = MaterialTheme.colorScheme.primary,
                            onClick = { },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "5",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = MaterialTheme.colorScheme.primary,
                            onClick = { },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "7",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = MaterialTheme.colorScheme.primary,
                            onClick = { },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )

                        StyledButton(
                            text = "8",
                            icon = { Icon(
                                Icons.Outlined.DirectionsBusFilled, contentDescription = null, modifier = Modifier.size(24.dp)) },
                            color = MaterialTheme.colorScheme.primary,
                            onClick = { },
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJoinLobbyScreen()
{
    JoinLobby()
}