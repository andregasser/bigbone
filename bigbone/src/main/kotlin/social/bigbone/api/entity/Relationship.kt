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
     * Are you receiving this user’s boosts in your home timeline?
     */
    @SerializedName("showing_reblogs")
    val isShowingReblogs: Boolean = false,

    /**
     * Have you enabled notifications for this user?
     */
    @SerializedName("notifying")
    val notifying: Boolean = false,

    /**
     * Which languages are you following from this user?
     */
    @SerializedName("languages")
    val languages: List<String> = emptyList(),

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
     * Is this user blocking you?
     */
    @SerializedName("blocked_by")
    val isBlockedBy: Boolean = false,

    /**
     * Are you muting this user?
     */
    @SerializedName("muting")
    val isMuting: Boolean = false,

    /**
     * Are you muting notifications from this user?
     */
    @SerializedName("muting_notifications")
    val isMutingNotifications: Boolean = false,

    /**
     * Do you have a pending follow request for this user?
     */
    @SerializedName("requested")
    val isRequested: Boolean = false,

    /**
     * Are you blocking this user’s domain?
     */
    @SerializedName("domain_blocking")
    val isDomainBlocking: Boolean = false,

    /**
     * Are you featuring this user on your profile?
     */
    @SerializedName("endorsed")
    val endorsed: Boolean = false,

    /**
     * This user’s profile bio.
     */
    @SerializedName("note")
    val note: String = ""
)
