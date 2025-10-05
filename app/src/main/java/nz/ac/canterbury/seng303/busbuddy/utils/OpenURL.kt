package nz.ac.canterbury.seng303.busbuddy.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri
import nz.ac.canterbury.seng303.busbuddy.R

fun openURL(context: Context, webLink: String) {
    val intent = Intent(Intent.ACTION_VIEW)

    intent.setData(webLink.toUri())
    context.startActivity(intent)

    Toast.makeText(context, "Opening: $webLink", Toast.LENGTH_SHORT).show()
}