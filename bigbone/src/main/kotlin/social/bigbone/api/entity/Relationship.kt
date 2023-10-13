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
     * Are you receiving this user’s boosts in your home timeline?
     */
    @SerialName("showing_reblogs")
    val isShowingReblogs: Boolean = false,

    /**
     * Have you enabled notifications for this user?
     */
    @SerialName("notifying")
    val notifying: Boolean = false,

    /**
     * Which languages are you following from this user?
     */
    @SerialName("languages")
    val languages: List<String> = emptyList(),

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
     * Is this user blocking you?
     */
    @SerialName("blocked_by")
    val isBlockedBy: Boolean = false,

    /**
     * Are you muting this user?
     */
    @SerialName("muting")
    val isMuting: Boolean = false,

    /**
     * Are you muting notifications from this user?
     */
    @SerialName("muting_notifications")
    val isMutingNotifications: Boolean = false,

    /**
     * Do you have a pending follow request for this user?
     */
    @SerialName("requested")
    val isRequested: Boolean = false,

    /**
     * Are you blocking this user’s domain?
     */
    @SerialName("domain_blocking")
    val isDomainBlocking: Boolean = false,

    /**
     * Are you featuring this user on your profile?
     */
    @SerialName("endorsed")
    val endorsed: Boolean = false,

    /**
     * This user’s profile bio.
     */
    @SerialName("note")
    val note: String = ""
)
