@file:Suppress("DEPRECATION")

package nz.ac.canterbury.seng303.busbuddy.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nz.ac.canterbury.seng303.busbuddy.R
import nz.ac.canterbury.seng303.busbuddy.utils.bounceClick

enum class RequestType {
    CAMERA(0),
    GALLERY(1);

    val requestType: Int

    constructor(requestType: Int) {
        this.requestType = requestType
    }
}
@Composable
fun NoPermissionScreen(onRequestPermission: () -> Unit, requestType: RequestType) {
    LocalContext.current

    NoPermissionContent(
        onRequestPermission = onRequestPermission,
        requestType = requestType
    )
}

@Composable
fun NoPermissionContent(onRequestPermission: () -> Unit, requestType: RequestType) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    LazyColumn (
        modifier = Modifier
            .padding(vertical = 24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        item {
            Text(
                text = "Attention",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 60.sp,
                    fontStyle = FontStyle.Italic,
                    shadow = Shadow(
                        color = Color.DarkGray,
                        offset = Offset(10f, 10f),
                        blurRadius = 10f)
                )
            )
            when (requestType) {
                RequestType.CAMERA -> {
                    Text(
                        text = "This app requires camera permissions",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Italic,
                            shadow = Shadow(
                                color = Color.DarkGray,
                                offset = Offset(5f, 5f),
                                blurRadius = 5f
                            )
                        )
                    )
                }

                RequestType.GALLERY -> {
                    Text(
                        text = "This app requires gallery permissions",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Italic,
                            shadow = Shadow(
                                color = Color.DarkGray,
                                offset = Offset(5f, 5f),
                                blurRadius = 5f
                            )
                        )
                    )
                }
            }
        }
        item {
            when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    Spacer(Modifier.height(256.dp))}

                Configuration.ORIENTATION_LANDSCAPE -> {
                    Spacer(Modifier.height(28.dp))}

                Configuration.ORIENTATION_SQUARE -> {
                    Spacer(Modifier.height(128.dp))}

                Configuration.ORIENTATION_UNDEFINED -> {
                    Spacer(Modifier.height(128.dp))} }
            }
        item {
            when (requestType) {
                RequestType.CAMERA -> {
                    Text(
                        text = "Grant Camera Permissions",
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(10.dp)
                    )
                }

                RequestType.GALLERY -> {
                    Text(
                        text = "Grant Gallery Permissions",
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }

        item {
            Button(
                onRequestPermission,
                modifier = Modifier.bounceClick(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(93, 63, 211)
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "GrantPermission",
                    tint = Color.White
                )
                Text(
                    text = "Grant Permissions",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(all = 10.dp)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_background),
                    contentDescription = "GrantPermission",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewNoPermissionContext()
{
    LocalContext.current
    NoPermissionContent(onRequestPermission = {}, RequestType.CAMERA)
}