package nz.ac.canterbury.seng303.busbuddy.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri
import nz.ac.canterbury.seng303.busbuddy.R

fun openApplication(context: Context, packageName: String) {

    var launchIntent: Intent? = context.packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        // We found the activity now start the activity
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val chooser = Intent.createChooser(launchIntent, /* title */ null)
        context.startActivity(chooser)
    } else {
        val resetPermsDialogue = AlertDialog.Builder(context)
        resetPermsDialogue.setIcon(R.drawable.ic_launcher_foreground)
        resetPermsDialogue.setTitle("Application Not Found!")
        resetPermsDialogue.setMessage("Would you like to browse the Google Play Store for a compatible app?")
            .setCancelable(false)
            .setPositiveButton("Open") { dialog, id ->
                // Bring user to the market or let them choose an app?
                launchIntent = Intent(Intent.ACTION_VIEW)
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                launchIntent.setData(("market://details?id=$packageName").toUri())
                context.startActivity(launchIntent)
            }
            .setNegativeButton("Cancel") { dialog, id ->
                // Dismiss the dialog
                dialog.dismiss()
            }
        val alert = resetPermsDialogue.create()
        alert.show()
    }

    Toast.makeText(context, "Opening: $packageName", Toast.LENGTH_SHORT).show()
}