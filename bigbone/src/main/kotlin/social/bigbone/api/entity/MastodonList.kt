package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a list of some users that the authenticated user follows.
 * @see <a href="https://docs.joinmastodon.org/entities/List/">Mastodon API List</a>
 */
data class MastodonList(
    /**
     * The internal database ID of the list.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * The user-defined title of the list.
     */
    @SerializedName("title")
    val title: String = ""
)
