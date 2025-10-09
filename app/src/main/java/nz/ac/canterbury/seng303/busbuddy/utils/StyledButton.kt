package nz.ac.canterbury.seng303.busbuddy.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StyledButton(
    text: String,
    icon: @Composable () -> Unit,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: androidx.compose.foundation.shape.CornerBasedShape = RoundedCornerShape(16.dp),
    verticalPadding: Dp = 20.dp,
    fontSize: TextUnit = 16.sp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = shape,
        modifier = modifier
            .shadow(6.dp, shape)
            .bounceClick(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = verticalPadding)
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            softWrap = false
        )
    }
}