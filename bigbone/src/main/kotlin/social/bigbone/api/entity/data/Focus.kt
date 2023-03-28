package social.bigbone.api.entity.data

import com.google.gson.annotations.SerializedName

/**
 * Contains the coordinates to be used for smart thumbnail cropping.
 * For more details, please have a look at https://docs.joinmastodon.org/api/guidelines/#focal-points
 */
data class Focus(
    @SerializedName("x")
    val x: Float,

    @SerializedName("y")
    val y: Float
)

fun Focus.asString(): String = "$x, $y"
