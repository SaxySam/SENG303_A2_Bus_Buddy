package nz.ac.canterbury.seng303.busbuddy.usecases

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import org.koin.core.annotation.Factory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nz.ac.canterbury.seng303.busbuddy.R
import java.io.OutputStream
import java.lang.Exception

@Factory
class SavePhotoToGalleryUseCase(private  val context: Context) {
    suspend fun call(capturePhotoBitmap: Bitmap): Result<Unit> = withContext(Dispatchers.IO) {

        val resolver: ContentResolver = context.applicationContext.contentResolver

        val imageCollection: Uri = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            else -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val nowTimeStamp: Long = System.currentTimeMillis()
        val imageContentValues: ContentValues = ContentValues().apply {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.DATE_TAKEN, nowTimeStamp)
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/" + "Bus Buddy")
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                put(MediaStore.Images.Media.DATE_TAKEN, nowTimeStamp)
                put(MediaStore.Images.Media.DATE_ADDED, nowTimeStamp)
                put(MediaStore.Images.Media.DATE_MODIFIED, nowTimeStamp)
                put(MediaStore.Images.Media.AUTHOR, "Samuel Kennedy")
                put(MediaStore.Images.Media.DESCRIPTION, "Jetpack Compose CameraX Image")
            }
        }

        val imageMediaStoreUri: Uri? = resolver.insert(imageCollection, imageContentValues)

        val result: Result<Unit> = imageMediaStoreUri?.let { uri ->
            runCatching {
                resolver.openOutputStream(uri).use {outputStream: OutputStream? ->
                    checkNotNull(outputStream) { "Couldn't create file for gallery, MediaStore output stream is Null" }
                    capturePhotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    imageContentValues.clear()
                    imageContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                    resolver.update(uri, imageContentValues, null, null)
                }

                Result.success(Unit)
            }.getOrElse { exception: Throwable ->
                exception.message?.let(::println)
                resolver.delete(uri, null, null)
                Result.failure(exception)
            }
        } ?: run {
            Result.failure(Exception("Couldn't create file for gallery"))
        }

        return@withContext result
    }
}