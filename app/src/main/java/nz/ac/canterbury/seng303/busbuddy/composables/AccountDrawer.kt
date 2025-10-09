package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LiveHelp
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.LiveHelp
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbsUpDown
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ThumbsUpDown
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.busbuddy.data.NavigationItem
import nz.ac.canterbury.seng303.busbuddy.ui.theme.BlueGradientStart
import nz.ac.canterbury.seng303.busbuddy.ui.theme.Typography
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick
import nz.ac.canterbury.seng303.busbuddy.R.drawable.ic_launcher_foreground

class AccountDrawer() {

    @Composable
    fun Screen(modifier: Modifier = Modifier, isSignedIn: Boolean = true)
    {
        val loggedInItems = listOf(

            NavigationItem(
                title = "My Account",
                selectedIcon = Icons.Filled.AccountCircle,
                unselectedIcon = Icons.Outlined.AccountCircle,
            ),
            NavigationItem(
                title = "Updates",
                selectedIcon = Icons.Filled.NotificationsActive,
                unselectedIcon = Icons.Outlined.NotificationsActive,
                badgeCount = 1
            ),
            NavigationItem(
                title = "Help",
                selectedIcon = Icons.AutoMirrored.Filled.LiveHelp,
                unselectedIcon = Icons.AutoMirrored.Outlined.LiveHelp,
            ),
            NavigationItem(
                title = "Feedback",
                selectedIcon = Icons.Filled.ThumbsUpDown,
                unselectedIcon = Icons.Outlined.ThumbsUpDown,
            ),
            NavigationItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
            ),
            NavigationItem(
                title = "About This App",
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info,
            ),
            NavigationItem(
                title = "Log Out",
                selectedIcon = Icons.AutoMirrored.Filled.Logout,
                unselectedIcon = Icons.AutoMirrored.Outlined.Logout,
            )
        )

        val loggedOutItems = listOf(

            NavigationItem(
                title = "Log In",
                selectedIcon = Icons.AutoMirrored.Filled.Login,
                unselectedIcon = Icons.AutoMirrored.Outlined.Login,
            ),
            NavigationItem(
                title = "Help",
                selectedIcon = Icons.AutoMirrored.Filled.LiveHelp,
                unselectedIcon = Icons.AutoMirrored.Outlined.LiveHelp,
            ),
            NavigationItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
            ),
            NavigationItem(
                title = "About This App",
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info,
            )
        )

        val currentDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable { mutableIntStateOf((0)) }

        ModalNavigationDrawer(
            drawerState = currentDrawerState,
            gesturesEnabled = true,
            drawerContent = {
                ModalDrawerSheet {
                    if (isSignedIn) {
                        Image(
                            painter = painterResource(ic_launcher_foreground),
                            contentDescription = "My Profile Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                                .size(128.dp)
                                .shadow(8.dp, RoundedCornerShape(50))
                                .background(
                                    MaterialTheme.colorScheme.background,
                                    RoundedCornerShape(50)
                                ),
                        )
                        Text(
                            "My Account Name:",
                            color = Color.Black,
                            style = Typography.headlineMedium,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    } else {
                        Image(
                            painter = painterResource(ic_launcher_foreground),
                            contentDescription = "Missing Profile Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally)
                                .size(64.dp)
                                .shadow(8.dp, RoundedCornerShape(50))
                                .background(
                                    MaterialTheme.colorScheme.background,
                                    RoundedCornerShape(50)
                                ),
                        )
                        Text(
                            "Not Signed In",
                            color = Color.Black,
                            style = Typography.headlineSmall,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(16.dp))
                    (if (isSignedIn) loggedInItems else loggedOutItems).forEachIndexed<NavigationItem> { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = item.title,
                                    color = Color.Black
                                )
                            },
                            selected = (index == selectedItemIndex),
                            onClick = {
                                selectedItemIndex = index;
                                scope.launch { currentDrawerState.close() }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            badge = {
                                item.badgeCount?.let<Int, Unit> { Text(item.badgeCount.toString()) }
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopBar(
                        navController = rememberNavController(),
                        onOpenDrawer = {
                            scope.launch {
                                currentDrawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    )
                }
            ) { padding ->
                ScreenContent(Modifier.padding(padding))
            }

        }
    }


    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun TopBar(
        modifier: Modifier = Modifier,
        onOpenDrawer: () -> Unit,
        navController: NavController
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = { },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = BlueGradientStart),
            navigationIcon = {
                Box(modifier = Modifier.fillMaxWidth()) {
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
                    if (navController.currentDestination?.route != "HomeScreen")
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

        )
    }

    @Composable
    fun ScreenContent(modifier: Modifier = Modifier) {

        Box(modifier = Modifier.fillMaxSize().padding(24.dp).background(Color.Red))
        rememberNavController().currentDestination;
    }

    @Preview
    @Composable
    fun PreviewScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            val accountDrawer = AccountDrawer()
            accountDrawer.Screen()
            rememberDrawerState(initialValue = DrawerValue.Open)
        }
    }
}
