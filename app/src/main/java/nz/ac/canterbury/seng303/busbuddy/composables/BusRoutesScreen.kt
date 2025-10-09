package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import nz.ac.canterbury.seng303.busbuddy.utils.RoutesDropDown
import nz.ac.canterbury.seng303.busbuddy.utils.SectionCard
import nz.ac.canterbury.seng303.busbuddy.viewmodels.RoutesViewModel

@Composable
fun BusRoutesScreen() {
    val context = LocalContext.current
    val viewModel = remember { RoutesViewModel(context) }

    Scaffold(
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
            Box (modifier = Modifier.padding(20.dp)) {
                SectionCard(title = "Routes") {
                    // Add bus routes drop down
                    RoutesDropDown(viewModel)
                    // Display proximity to next stop



//                val busMap: Painter = painterResource(id = R.drawable.metro_map_christchurch)
//                ZoomableImage(
//                    busMap = busMap
//                )
                }
            }
        }
    }
}