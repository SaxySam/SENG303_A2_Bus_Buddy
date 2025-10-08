package nz.ac.canterbury.seng303.busbuddy.models;

import androidx.compose.runtime.Immutable;

@Immutable
data class BusStop(
    val name: String,
    val etaMinutes: Int
)