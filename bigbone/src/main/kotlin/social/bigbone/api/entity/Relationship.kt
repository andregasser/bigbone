package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the relationship between accounts, such as following / blocking / muting / etc.
 * @see <a href="https://docs.joinmastodon.org/entities/Relationship/">Mastodon API Relationship</a>
 */
@Serializable
data class Relationship(
    /**
     * The account ID.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * Are you following this user?
     */
    @SerialName("following")
    val isFollowing: Boolean = false,

    /**
     * Are you followed by this user?
     */
    @SerialName("followed_by")
    val isFollowedBy: Boolean = false,

    /**
     * Are you blocking this user?
     */
    @SerialName("blocking")
    val isBlocking: Boolean = false,

    /**
     * Are you muting this user?
     */
    @SerialName("muting")
    val isMuting: Boolean = false,

    /**
     * Do you have a pending follow request for this user?
     */
    @SerialName("requested")
    val isRequested: Boolean = false
)
