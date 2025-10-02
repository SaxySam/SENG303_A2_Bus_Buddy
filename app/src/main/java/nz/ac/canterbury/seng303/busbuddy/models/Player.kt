package nz.ac.canterbury.seng303.busbuddy.models

import androidx.compose.runtime.Immutable

@Immutable
data class Player (
    val name: String,
    val currentScore: Int
)