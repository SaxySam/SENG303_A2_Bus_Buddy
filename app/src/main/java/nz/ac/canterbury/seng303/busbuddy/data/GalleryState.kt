package nz.ac.canterbury.seng303.busbuddy.data

data class GalleryState(
    val isLoading: Boolean = false,
    val isPermissionAccepted: Boolean = false,
    val mediaItems: List<MediaItem> = emptyList(),
    val clickedIndex: Int = -1
) {
    data class MediaItem(
        val uri: String,
        val type: MediaType
    ) {
        enum class MediaType {
            IMAGE(0),
            VIDEO(1);

            val mediaType: Int

            constructor(mediaType: Int) {
                this.mediaType = mediaType
            }
        }
    }
}