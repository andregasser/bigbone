package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.entity.data.History

/**
 * Represents a hashtag used within the content of a status.
 * @see <a href="https://docs.joinmastodon.org/entities/Tag/">Mastodon API Tag</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class Tag(
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
    val following: Boolean? = null
)
