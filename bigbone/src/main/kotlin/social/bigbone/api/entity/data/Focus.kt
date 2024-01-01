package social.bigbone.api.entity.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the coordinates to be used for smart thumbnail cropping.
 * For more details, please have a look at https://docs.joinmastodon.org/api/guidelines/#focal-points
 */
@Serializable
data class Focus(
    @SerialName("x")
    val x: Float,

    @SerialName("y")
    val y: Float
) {
    init {
        require(x in -1.0..1.0) { "x coordinate must be between -1.0 and +1.0 but was $x" }
        require(y in -1.0..1.0) { "y coordinate must be between -1.0 and +1.0 but was $y" }
    }
    override fun toString(): String = "$x, $y"
}
