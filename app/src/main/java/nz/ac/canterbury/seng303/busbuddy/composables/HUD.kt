package nz.ac.canterbury.seng303.busbuddy.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nz.ac.canterbury.seng303.busbuddy.models.BusStop
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.busbuddy.models.Player

@Composable
fun HUD(navController: NavController,
        running: Boolean,
        player: Player,
        nextStop: BusStop,
        modifier: Modifier = Modifier) {
    if (running) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xAA000000), Color(0x66000000))
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.MonetizationOn,
                    contentDescription = "Score",
                    tint = Color(0xFFFFD54F),
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = player.currentScore.toString(),
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = nextStop.name,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = "${nextStop.etaMinutes} mins",
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x99000000)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1E1E1E))
                    .padding(32.dp)
            ) {
                Text(
                    text = "GAME OVER",
                    color = Color.Red,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Score: ${player.currentScore}",
                    color = Color.White,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { navController.navigate("GamePlayScreen") },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Restart",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}