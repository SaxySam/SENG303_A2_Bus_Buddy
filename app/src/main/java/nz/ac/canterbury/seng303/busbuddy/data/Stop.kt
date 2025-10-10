package nz.ac.canterbury.seng303.busbuddy.data

import kotlinx.serialization.Serializable

@Serializable
data class Stop(
    val name: String,
    val lat: Double,
    val lon: Double
)
