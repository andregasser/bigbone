package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a user of Mastodon and their associated profile.
 * @see <a href="https://docs.joinmastodon.org/entities/Account/">Mastodon API Account</a>
 */
data class Account(
    /**
     * The account id.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * The username of the account, not including domain.
     */
    @SerializedName("username")
    val username: String = "",

    /**
     * The Webfinger account URI. Equal to username for local users, or username@domain for remote users.
     */
    @SerializedName("acct")
    val acct: String = "",

    /**
     * The location of the user’s profile page.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * The profile’s display name.
     */
    @SerializedName("display_name")
    val displayName: String = "",

    /**
     * The profile’s bio or description.
     */
    @SerializedName("note")
    val note: String = "",

    /**
     * An image icon that is shown next to statuses and in the profile.
     */
    @SerializedName("avatar")
    val avatar: String = "",

    /**
     * A static version of the avatar. Equal to [avatar] if its value is a static image; different if avatar is an animated GIF.
     */
    @SerializedName("avatar_static")
    val avatarStatic: String = "",

    /**
     * An image banner that is shown above the profile and in profile cards.
     */
    @SerializedName("header")
    val header: String = "",

    /**
     * A static version of the header. Equal to [header] if its value is a static image; different if header is an animated GIF.
     */
    @SerializedName("header_static")
    val headerStatic: String = "",

    /**
     * Whether the account manually approves follow requests.
     */
    @SerializedName("locked")
    val isLocked: Boolean = false,

    /**
     * Additional metadata attached to a profile as name-value pairs.
     */
    @SerializedName("fields")
    val fields: List<Field> = emptyList(),

    /**
     * Custom emoji entities to be used when rendering the profile.
     */
    @SerializedName("emojis")
    val emojis: List<CustomEmoji> = emptyList(),

    /**
     * Indicates that the account may perform automated actions, may not be monitored, or identifies as a robot.
     */
    @SerializedName("bot")
    val isBot: Boolean = false,

    /**
     *  Indicates that the account represents a Group actor.
     */
    @SerializedName("group")
    val isGroup: Boolean = false,

    /**
     * Whether the account has opted into discovery features such as the profile directory.
     */
    @SerializedName("discoverable")
    val isDiscoverable: Boolean? = null,

    /**
     * Whether the local user has opted out of being indexed by search engines.
     */
    @SerializedName("noindex")
    val isNotIndexed: Boolean? = null,

    /**
     * Indicates that the profile is currently inactive and that its user has moved to a new account.
     */
    @SerializedName("moved")
    val moved: Account? = null,

    /**
     * An extra attribute returned only when an account is suspended.
     */
    @SerializedName("suspended")
    val isSuspended: Boolean? = null,

    /**
     * An extra attribute returned only when an account is silenced.
     * If true, indicates that the account should be hidden behind a warning screen.
     */
    @SerializedName("limited")
    val isLimited: Boolean? = null,

    /**
     * When the account was created (ISO 8601 Datetime).
     */
    @SerializedName("created_at")
    val createdAt: String = "",

    /**
     * When the most recent status was posted (ISO 8601 Datetime).
     */
    @SerializedName("last_status_at")
    val lastStatusAt: String? = null,

    /**
     * How many statuses are attached to this account.
     */
    @SerializedName("statuses_count")
    val statusesCount: Long = 0,

    /**
     * The reported followers of this profile.
     */
    @SerializedName("followers_count")
    val followersCount: Long = 0,

    /**
     * The reported follows of this profile.
     */
    @SerializedName("following_count")
    val followingCount: Long = 0
) {
    /**
     * Specifies a name-value pair as used in [fields] of the [Account] entity.
     */
    data class Field(
        /**
         * The key of a given field’s key-value pair.
         */
        @SerializedName("name")
        val name: String = "",

        /**
         * The value associated with the [name] key (HTML string).
         */
        @SerializedName("value")
        val value: String = "",

        /**
         * Timestamp of when the server verified a URL value for a rel=“me” link.
         * ISO 8601 Datetime string if [value] is a verified URL. Otherwise, null.
         */
        @SerializedName("verified_at")
        val verifiedAt: String? = null
    )
}
