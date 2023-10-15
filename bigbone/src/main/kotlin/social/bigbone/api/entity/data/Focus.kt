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
    override fun toString(): String = "$x, $y"
}
