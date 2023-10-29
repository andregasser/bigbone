package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime

/**
 * Represents a user of Mastodon and their associated profile.
 * @see <a href="https://docs.joinmastodon.org/entities/Account/">Mastodon API Account</a>
 */
@Serializable
data class Account(
    /**
     * The account id.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The username of the account, not including domain.
     */
    @SerialName("username")
    val username: String = "",

    /**
     * The Webfinger account URI. Equal to username for local users, or username@domain for remote users.
     */
    @SerialName("acct")
    val acct: String = "",

    /**
     * The location of the user’s profile page.
     */
    @SerialName("url")
    val url: String = "",

    /**
     * The profile’s display name.
     */
    @SerialName("display_name")
    val displayName: String = "",

    /**
     * The profile’s bio or description.
     */
    @SerialName("note")
    val note: String = "",

    /**
     * An image icon that is shown next to statuses and in the profile.
     */
    @SerialName("avatar")
    val avatar: String = "",

    /**
     * A static version of the avatar. Equal to [avatar] if its value is a static image; different if avatar is an animated GIF.
     */
    @SerialName("avatar_static")
    val avatarStatic: String = "",

    /**
     * An image banner that is shown above the profile and in profile cards.
     */
    @SerialName("header")
    val header: String = "",

    /**
     * A static version of the header. Equal to [header] if its value is a static image; different if header is an animated GIF.
     */
    @SerialName("header_static")
    val headerStatic: String = "",

    /**
     * Whether the account manually approves follow requests.
     */
    @SerialName("locked")
    val isLocked: Boolean = false,

    /**
     * Additional metadata attached to a profile as name-value pairs.
     */
    @SerialName("fields")
    val fields: List<Field> = emptyList(),

    /**
     * Custom emoji entities to be used when rendering the profile.
     */
    @SerialName("emojis")
    val emojis: List<CustomEmoji> = emptyList(),

    /**
     * Indicates that the account may perform automated actions, may not be monitored, or identifies as a robot.
     */
    @SerialName("bot")
    val isBot: Boolean = false,

    /**
     *  Indicates that the account represents a Group actor.
     */
    @SerialName("group")
    val isGroup: Boolean = false,

    /**
     * Whether the account has opted into discovery features such as the profile directory.
     */
    @SerialName("discoverable")
    val isDiscoverable: Boolean? = null,

    /**
     * Whether the local user has opted out of being indexed by search engines.
     */
    @SerialName("noindex")
    val isNotIndexed: Boolean? = null,

    /**
     * Indicates that the profile is currently inactive and that its user has moved to a new account.
     */
    @SerialName("moved")
    val moved: Account? = null,

    /**
     * An extra attribute returned only when an account is suspended.
     */
    @SerialName("suspended")
    val isSuspended: Boolean? = null,

    /**
     * An extra attribute returned only when an account is silenced.
     * If true, indicates that the account should be hidden behind a warning screen.
     */
    @SerialName("limited")
    val isLimited: Boolean? = null,

    /**
     * When the account was created (ISO 8601 Datetime).
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * When the most recent status was posted.
     * String (ISO 8601 Date), or null if no statuses
     */
    @SerialName("last_status_at")
    @Serializable(with = DateTimeSerializer::class)
    val lastStatusAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * How many statuses are attached to this account.
     */
    @SerialName("statuses_count")
    val statusesCount: Long = 0,

    /**
     * The reported followers of this profile.
     */
    @SerialName("followers_count")
    val followersCount: Long = 0,

    /**
     * The reported follows of this profile.
     */
    @SerialName("following_count")
    val followingCount: Long = 0
) {
    /**
     * Specifies a name-value pair as used in [fields] of the [Account] entity.
     */
    @Serializable
    data class Field(
        /**
         * The key of a given field’s key-value pair.
         */
        @SerialName("name")
        val name: String = "",

        /**
         * The value associated with the [name] key (HTML string).
         */
        @SerialName("value")
        val value: String = "",

        /**
         * Timestamp of when the server verified a URL value for a rel=“me” link.
         * ISO 8601 Datetime string if [value] is a verified URL. Otherwise, null.
         */
        @SerialName("verified_at")
        @Serializable(with = DateTimeSerializer::class)
        val verifiedAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable
    )
}
