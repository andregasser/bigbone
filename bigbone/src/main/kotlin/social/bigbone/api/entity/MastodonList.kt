package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a list of some users that the authenticated user follows.
 * @see <a href="https://docs.joinmastodon.org/entities/List/">Mastodon API List</a>
 */
@Serializable
data class MastodonList(
    /**
     * The internal database ID of the list.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The user-defined title of the list.
     */
    @SerialName("title")
    val title: String = "",

    /**
     * Which replies should be shown in the list.
     * @see [RepliesPolicy]
     */
    @SerialName("replies_policy")
    val repliesPolicy: String = RepliesPolicy.List.value
) {
    /**
     * Specifies the replies policy for a list.
     */
    enum class RepliesPolicy(val value: String) {
        Followed("followed"),
        List("list"),
        None("none")
    }
}
