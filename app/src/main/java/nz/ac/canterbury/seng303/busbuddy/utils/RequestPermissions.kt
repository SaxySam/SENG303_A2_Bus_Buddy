@file:Suppress("DEPRECATION")

package nz.ac.canterbury.seng303.busbuddy.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class RequestType {
    CAMERA(0),
    GALLERY(1),
    LOCATION(2),
    ORIENTATION(3),
    ACCELERATION(4);

    val requestType: Int

    constructor(requestType: Int) {
        this.requestType = requestType
    }
}

@Composable
fun NoPermissionContent(onRequestPermission: () -> Unit, requestType: RequestType) {
    LazyColumn { item { onRequestPermission() } }
}

@Preview
@Composable
fun PreviewNoPermissionContext() {
    LocalContext.current
    NoPermissionContent(onRequestPermission = {}, RequestType.CAMERA)
}