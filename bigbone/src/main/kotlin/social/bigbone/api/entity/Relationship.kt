package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents the relationship between accounts, such as following / blocking / muting / etc.
 * @see <a href="https://docs.joinmastodon.org/entities/Relationship/">Mastodon API Relationship</a>
 */
data class Relationship(
    /**
     * The account ID.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * Are you following this user?
     */
    @SerializedName("following")
    val isFollowing: Boolean = false,

    /**
     * Are you followed by this user?
     */
    @SerializedName("followed_by")
    val isFollowedBy: Boolean = false,

    /**
     * Are you blocking this user?
     */
    @SerializedName("blocking")
    val isBlocking: Boolean = false,

    /**
     * Are you muting this user?
     */
    @SerializedName("muting")
    val isMuting: Boolean = false,

    /**
     * Do you have a pending follow request for this user?
     */
    @SerializedName("requested")
    val isRequested: Boolean = false
)
