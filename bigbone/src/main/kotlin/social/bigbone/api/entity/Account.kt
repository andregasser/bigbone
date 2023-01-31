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
    val userName: String = "",

    /**
     * The Webfinger account URI. Equal to username for local users, or username@domain for remote users.
     */
    @SerializedName("acct")
    val acct: String = "",

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
     * The location of the user’s profile page.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * An image icon that is shown next to statuses and in the profile.
     */
    @SerializedName("avatar")
    val avatar: String = "",

    /**
     * An image banner that is shown above the profile and in profile cards.
     */
    @SerializedName("header")
    val header: String = "",

    /**
     * Whether the account manually approves follow requests.
     */
    @SerializedName("locked")
    val isLocked: Boolean = false,

    /**
     * When the account was created (ISO 8601 Datetime).
     */
    @SerializedName("created_at")
    val createdAt: String = "",

    /**
     * The reported followers of this profile.
     */
    @SerializedName("followers_count")
    val followersCount: Int = 0,

    /**
     * The reported follows of this profile.
     */
    @SerializedName("following_count")
    val followingCount: Int = 0,

    /**
     * How many statuses are attached to this account.
     */
    @SerializedName("statuses_count")
    val statusesCount: Int = 0,

    @SerializedName("emojis")
    val emojis: List<Emoji> = emptyList()
)
