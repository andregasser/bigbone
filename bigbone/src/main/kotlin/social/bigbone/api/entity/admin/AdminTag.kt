package social.bigbone.api.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.entity.Tag
import social.bigbone.api.entity.data.History

/**
 * Represents a hashtag used within the content of a status.
 *
 * This version contains additional Admin-related attributes not available in [Tag].
 *
 * @see <a href="https://docs.joinmastodon.org/entities/Tag/#admin">Mastodon API Admin::Tag</a>
 */
@Serializable
data class AdminTag(
    /**
     * The value of the hashtag after the # sign.
     */
    @SerialName("name")
    val name: String = "",

    /**
     * A link to the hashtag on the instance.
     */
    @SerialName("url")
    val url: String = "",

    /**
     * Usage statistics for given days (typically the past week).
     */
    @SerialName("history")
    val history: List<History> = emptyList(),

    /**
     * Whether the current token’s authorized user is following this tag.
     */
    @SerialName("following")
    val following: Boolean? = null,

    /**
     * The ID of the Tag in the database.
     * String cast from an Integer, but not guaranteed to be a number.
     */
    @SerialName("id")
    val id: String,

    /**
     * Whether the hashtag has been approved to the trend.
     */
    @SerialName("trendable")
    val trendable: Boolean,

    /**
     * Whether the hashtag has not been disabled from auto-linking.
     */
    @SerialName("usable")
    val usable: Boolean,

    /**
     * Whether the hashtag has not been reviewed yet to approve or deny its trending.
     */
    @SerialName("requires_review")
    val requiresReview: Boolean
)
