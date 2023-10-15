package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the last read position within a user's timelines.
 * @see <a href="https://docs.joinmastodon.org/entities/Marker/">Mastodon API Marker</a>
 */
@Serializable
data class Markers(
    /**
     * Represents the last read position within a user's notification timeline.
     */
    @SerialName("notifications")
    val notifications: Marker? = null,

    /**
     * Represents the last read position within a user's home timeline.
     */
    @SerialName("home")
    val home: Marker? = null
)
