package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a hashtag that is featured on a profile.
 * @see <a href="https://docs.joinmastodon.org/entities/FeaturedTag/">Mastodon API FeaturedTag</a>
 */
data class FeaturedTag(
    /**
     * The internal ID of the featured tag in the database.
     */
    @SerializedName("id")
    val id: String = "",

    /**
     * The name of the hashtag being featured.
     */
    @SerializedName("name")
    val name: String = "",

    /**
     * A link to all statuses by a user that contain this hashtag.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * A link to all statuses by a user that contain this hashtag.
     */
    @SerializedName("statuses_count")
    val statusesCount: Int = 0,

    /**
     * The timestamp of the last authored status containing this hashtag.
     */
    @SerializedName("last_status_at")
    val lastStatusAt: String = ""
)
