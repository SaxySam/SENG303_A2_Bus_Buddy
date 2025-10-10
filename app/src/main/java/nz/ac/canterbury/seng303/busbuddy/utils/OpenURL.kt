package nz.ac.canterbury.seng303.busbuddy.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri
import nz.ac.canterbury.seng303.busbuddy.R

enum class ShowToast
{
    HIDE(0),
    SHOW(1);

    val showToast: Int

    constructor(showToast: Int) {
        this.showToast = showToast
    }
}

fun openURL(context: Context, webLink: String, showToast: ShowToast) {
    val intent = Intent(Intent.ACTION_VIEW)

    intent.setData(webLink.toUri())
    context.startActivity(intent)

    when (showToast) {
        ShowToast.SHOW -> {
            Toast.makeText(context,
                context.getString(R.string.OpeningApp, webLink), Toast.LENGTH_SHORT).show()
        }
        ShowToast.HIDE -> {}
    }
}