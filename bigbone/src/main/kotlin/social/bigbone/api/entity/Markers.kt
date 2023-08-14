package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents the last read position within a user's timelines.
 * @see <a href="https://docs.joinmastodon.org/entities/Marker/">Mastodon API Marker</a>
 */
data class Markers(
    /**
     * Represents the last read position within a user's notification timeline.
     */
    @SerializedName("notifications")
    val notifications: Marker? = null,

    /**
     * Represents the last read position within a user's home timeline.
     */
    @SerializedName("home")
    val home: Marker? = null
)
