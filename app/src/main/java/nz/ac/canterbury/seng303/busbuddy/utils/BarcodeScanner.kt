package nz.ac.canterbury.seng303.busbuddy.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.MutableStateFlow

class BarcodeScanner(appContext: Context) {

    /**
     * From the docs: If you know which barcode formats you expect to read,
     * you can improve the speed of the barcode detector by configuring it to only detect those formats
     */
    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .allowManualInput()
        .build()

    private val scanner = GmsBarcodeScanning.getClient(appContext, options)
    val barCodeResults = MutableStateFlow<String?>(null)

    fun startScan()
//        (onSuccess: (Barcode) -> Unit,
//        onCancel: () -> Unit,
//        onFailure: (Exception) -> Unit)
    {
        try {
            scanner.startScan()
                .addOnSuccessListener { barcode ->
                    // Task Completed Successfully
                    barCodeResults.value = barcode.displayValue

                    val result = barcode.rawValue
                    Log.d(TAG, "initiateScanner: $result")
                    // check the value - URL, TEXT, etc.
                    when (barcode.valueType) {
                        Barcode.TYPE_URL -> {
                            Log.d(TAG, "initiateScanner: ${barcode.valueType}")
                        }
                        else -> {
                            Log.d(TAG, "initiateScanner: ${barcode.valueType}")
                        }
                    }
                    // Display value
                    Log.d(TAG, "initiateScanner: Display value: ${barcode.displayValue}")
                    // Formate - FORMAT_AZTEC, etc.
                    Log.d(TAG, "initiateScanner: Format: ${barcode.format}")

//                    onSuccess(barcode)

                }
                .addOnCanceledListener {
                    // Task Cancelled
                    barCodeResults.value = "Cancelled"

//                    onCancel()
                }
                .addOnFailureListener { e ->
                    // Task failed with an Exception
                    barCodeResults.value = "Failed"

//                    onFailure(e)
                }
        }   catch (e: Exception) {
        }
    }
}