package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.busbuddy.R
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BlueGradientStart
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    onOpenDrawer: () -> Unit,
    navController: NavController,
) {
    val context = LocalContext.current
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = BlueGradientStart),
        navigationIcon = {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (navController.currentDestination?.route != "AccountDrawer") {
                    IconButton(
                        onClick = { onOpenDrawer() },
                        modifier = Modifier
                            .bounceClick()
                            .padding(all = 8.dp)
                            .align(Alignment.TopEnd)
                            .shadow(8.dp, RoundedCornerShape(50))
                            .background(
                                MaterialTheme.colorScheme.background,
                                RoundedCornerShape(50)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccountBox,
                            contentDescription = "Profile",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }

                } else {
                    IconButton(
                        onClick = { navController.navigate("HomeScreen") },
                        modifier = Modifier
                            .bounceClick()
                            .padding(all = 8.dp)
                            .align(Alignment.TopEnd)
                            .shadow(8.dp, RoundedCornerShape(50))
                            .background(
                                MaterialTheme.colorScheme.background,
                                RoundedCornerShape(50)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                if (navController.currentDestination?.route != "HomeScreen")
                    if (navController.currentDestination?.route == "AccountDrawer")
                    {
                        IconButton(
                            onClick = { onOpenDrawer() },
                            modifier = Modifier
                                .bounceClick()
                                .padding(all = 8.dp)
                                .align(Alignment.TopStart)
                                .shadow(8.dp, RoundedCornerShape(50))
                                .background(
                                    MaterialTheme.colorScheme.background,
                                    RoundedCornerShape(50)
                                )
                        ) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "Menu Button",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                    }
                else {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .bounceClick()
                                .padding(all = 8.dp)
                                .align(Alignment.TopStart)
                                .shadow(8.dp, RoundedCornerShape(50))
                                .background(
                                    MaterialTheme.colorScheme.background,
                                    RoundedCornerShape(50)
                                )
                        ) {
                            Icon(
                                Icons.Filled.ArrowBackIosNew,
                                contentDescription = "Back Button",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
            }
        }
    )
}