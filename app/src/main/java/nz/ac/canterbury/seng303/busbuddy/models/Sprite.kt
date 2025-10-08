package nz.ac.canterbury.seng303.busbuddy.models;

import androidx.annotation.DrawableRes
import nz.ac.canterbury.seng303.busbuddy.R

enum class SpriteType(
        @DrawableRes val resId: Int,
        val defaultSizeDp: Int = 55
) {
    Coin(R.drawable.coin, 55),
    Cone(R.drawable.cone, 80),
    Stop(R.drawable.stop, 70),
    Car(R.drawable.car, 110);

    companion object {
        val Sprites:List<SpriteType> = listOf(Coin, Coin, Coin, Coin, Coin, Cone, Stop, Car)
    }
}

data class Sprite(
        val id: Long,
        val type: SpriteType
)