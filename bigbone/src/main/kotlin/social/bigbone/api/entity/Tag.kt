package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName
import social.bigbone.api.entity.data.History

/**
 * Represents a hashtag used within the content of a status.
 * @see <a href="https://docs.joinmastodon.org/entities/Tag/">Mastodon API Tag</a>
 */
data class Tag(
    /**
     * The value of the hashtag after the # sign.
     */
    @SerializedName("name")
    val name: String = "",

    /**
     * A link to the hashtag on the instance.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * Usage statistics for given days (typically the past week).
     */
    @SerializedName("history")
    val history: List<History> = emptyList(),

    /**
     * Whether the current token’s authorized user is following this tag.
     */
    @SerializedName("following")
    val following: Boolean? = null
)
