package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime

/**
 * Represents the last read position within a specific timeline of the user.
 * @see <a href="https://docs.joinmastodon.org/entities/Marker/">Mastodon API Marker</a>
 */
@Serializable
data class Marker(
    /**
     * The ID of the most recently viewed entity.
     */
    @SerialName("last_read_id")
    val lastReadId: String = "0",

    /**
     * An incrementing counter, used for locking to prevent write conflicts.
     */
    @SerialName("version")
    val version: Int = 0,

    /**
     * The timestamp of when the marker was set.
     */
    @SerialName("updated_at")
    @Serializable(with = DateTimeSerializer::class)
    val updatedAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable
)
