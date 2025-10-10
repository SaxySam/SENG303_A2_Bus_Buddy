package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import nz.ac.canterbury.seng303.busbuddy.data.NavigationButtonItem
import nz.ac.canterbury.seng303.busbuddy.ui.theme.Typography
import nz.ac.canterbury.seng303.busbuddy.R.drawable.ic_launcher_foreground
import nz.ac.canterbury.seng303.busbuddy.utils.ThemeSwitcher

class AccountDrawer() {

    @Composable
    fun Screen(isSignedIn: Boolean = true, navController: NavController)
    {
        var darkTheme by remember {mutableStateOf(false)}
        val loggedInItems = listOf(

            NavigationButtonItem(
                title = "My Account",
                selectedIcon = Icons.Filled.AccountCircle,
                unselectedIcon = Icons.Outlined.AccountCircle,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "Updates",
                selectedIcon = Icons.Filled.NotificationsActive,
                unselectedIcon = Icons.Outlined.NotificationsActive,
                badgeCount = 1,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "Help",
                selectedIcon = Icons.AutoMirrored.Filled.LiveHelp,
                unselectedIcon = Icons.AutoMirrored.Outlined.LiveHelp,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "Feedback",
                selectedIcon = Icons.Filled.ThumbsUpDown,
                unselectedIcon = Icons.Outlined.ThumbsUpDown,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "About This App",
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "Log Out",
                selectedIcon = Icons.AutoMirrored.Filled.Logout,
                unselectedIcon = Icons.AutoMirrored.Outlined.Logout,
                onClickEvent = {}
            )
        )

        val loggedOutItems = listOf(

            NavigationButtonItem(
                title = "Log In",
                selectedIcon = Icons.AutoMirrored.Filled.Login,
                unselectedIcon = Icons.AutoMirrored.Outlined.Login,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "Help",
                selectedIcon = Icons.AutoMirrored.Filled.LiveHelp,
                unselectedIcon = Icons.AutoMirrored.Outlined.LiveHelp,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                onClickEvent = {}
            ),
            NavigationButtonItem(
                title = "About This App",
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info,
                onClickEvent = {}
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
                    (if (isSignedIn) loggedInItems else loggedOutItems).forEachIndexed<NavigationButtonItem> { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = item.title,
                                    color = Color.Black
                                )
                            },
                            selected = (index == selectedItemIndex),
                            onClick = {
                                item.onClickEvent;
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
                        navController = navController,
                        onOpenDrawer = {
                            scope.launch {
                                currentDrawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                    )
                },
                bottomBar = { NavBar(navController) }
            ) { padding ->
                ScreenContent(Modifier.padding(padding), darkTheme = darkTheme, onThemeUpdated = {darkTheme = !darkTheme}, isSignedIn)
            }

        }
    }

    @Composable
    fun ScreenContent(modifier: Modifier = Modifier,
                      darkTheme: Boolean,
                      onThemeUpdated: () -> Unit,
                      isSignedIn: Boolean){

        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 92.dp))
        {
            Column {
                Text(
                    "Your Account: ",
                    color = Color.Black,
                    style = Typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                )
                if (isSignedIn) {
                    Text(
                        "Display Name: ",
                        color = Color.Black,
                        style = Typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                    )
                    Text(
                        "Account Name: ",
                        color = Color.Black,
                        style = Typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                    )

                    Spacer(modifier = Modifier.height(64.dp))
                    Text(
                        "Most Travelled Bus Route: ",
                        color = Color.Black,
                        style = Typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                    Text(
                        "Highest Score: ",
                        color = Color.Black,
                        style = Typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                    Text(
                        "Active Since: ",
                        color = Color.Black,
                        style = Typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                    )
                }
            }
            ThemeSwitcher(darkTheme = darkTheme, onClick = onThemeUpdated, 0.95f, 0.85f, 0.6f)
        }
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
            accountDrawer.Screen(true, rememberNavController())
            rememberDrawerState(initialValue = DrawerValue.Open)
        }
    }
}
