package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents the last read position within a specific timeline of the user.
 * @see <a href="https://docs.joinmastodon.org/entities/Marker/">Mastodon API Marker</a>
 */
data class Marker(
    /**
     * The ID of the most recently viewed entity.
     */
    @SerializedName("last_read_id")
    val lastReadId: String = "0",

    /**
     * An incrementing counter, used for locking to prevent write conflicts.
     */
    @SerializedName("version")
    val version: Int = 0,

    /**
     * The timestamp of when the marker was set (ISO 8601 Datetime).
     */
    @SerializedName("updated_at")
    val updatedAt: String = ""
)
