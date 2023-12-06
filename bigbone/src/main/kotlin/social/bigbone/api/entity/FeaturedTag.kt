package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime

/**
 * Represents a hashtag that is featured on a profile.
 * @see <a href="https://docs.joinmastodon.org/entities/FeaturedTag/">Mastodon API FeaturedTag</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class FeaturedTag(
    /**
     * The internal ID of the featured tag in the database.
     */
    @SerialName("id")
    val id: String = "",

    /**
     * The name of the hashtag being featured.
     */
    @SerialName("name")
    val name: String = "",

    /**
     * A link to all statuses by a user that contain this hashtag.
     */
    @SerialName("url")
    val url: String = "",

    /**
     * A link to all statuses by a user that contain this hashtag.
     */
    @SerialName("statuses_count")
    val statusesCount: Int = 0,

    /**
     * The timestamp of the last authored status containing this hashtag.
     */
    @SerialName("last_status_at")
    @Serializable(with = DateTimeSerializer::class)
    val lastStatusAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable
)
