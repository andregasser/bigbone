package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime

/**
 * Represents an announcement set by an administrator.
 * @see <a href="https://docs.joinmastodon.org/entities/Announcement/"> Mastodon API Announcement </a>
 */

@Serializable
data class Announcement(
    /**
     * The ID of the announcement in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The text of the announcement.
     */
    @SerialName("content")
    val content: String = "",

    /**
     * When the announcement will start.
     */
    @SerialName("starts_at")
    @Serializable(with = DateTimeSerializer::class)
    val startsAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,

    /**
     * When the announcement will end.
     */
    @SerialName("ends_at")
    @Serializable(with = DateTimeSerializer::class)
    val endsAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,

    /**
     * Whether the announcement is currently active.
     */
    @SerialName("published")
    val published: Boolean = true,

    /**
     * Whether the announcement should start and end on dates only instead of datetimes.
     * Will be false if there is no starts_at or ends_at time.
     */
    @SerialName("all_day")
    val allDay: Boolean = true,

    /**
     * When the announcement was published.
     */
    @SerialName("published_at")
    @Serializable(with = DateTimeSerializer::class)
    val publishedAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,

    /**
     * When the announcement will end.
     */
    @SerialName("updated_at")
    @Serializable(with = DateTimeSerializer::class)
    val updatedAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,

    /**
     * Whether the announcement has been read by the current user.
     */
    @SerialName("read")
    val read: Boolean? = true,

    /**
     * Accounts mentioned in the announcement text.
     */
    @SerialName("mentions")
    val mentions: List<Announcement.Account> = emptyList(),

    /**
     * Statuses linked in the announcement text.
     */
    @SerialName("statuses")
    val statuses: List<Announcement.Status> = emptyList(),

    /**
     * Tags linked in the announcement text.
     */
    @SerialName("tags")
    val tags: List<Tag> = emptyList(),

    /**
     * Custom emoji used in the announcement text.
     */
    @SerialName("emojis")
    val emojis: List<CustomEmoji> = emptyList(),

    /**
     * Emoji reactions attached to the announcement.
     */
    @SerialName("reactions")
    val reactions: List<Reaction> = emptyList()
) {
    /**
     * Represents a user in Mastodon.
     * @see <a href="https://docs.joinmastodon.org/entities/Announcement/#Account">Mastodon API Announcement::Account</a>
     */
    @Serializable
    data class Account(
        /**
         * The account ID of the mentioned user.
         */
        @SerialName("id")
        val id: String = "0",

        /**
         * The username of the mentioned user.
         */
        @SerialName("username")
        val username: String = "",

        /**
         * The location of the mentioned user’s profile.
         */
        @SerialName("url")
        val url: String = "",

        /**
         * The webfinger acct: URI of the mentioned user.
         * Equivalent to username for local users, or username@domain for remote users.
         */
        @SerialName("acct")
        val acct: String = ""
    )

    /**
     * Describes the status of the announcement.
     * @see <a href="https://docs.joinmastodon.org/entities/Announcement/#Status">Mastodon API Announcement::Status</a>
     */
    @Serializable
    data class Status(
        /**
         * The ID of an attached Status in the database.
         */
        @SerialName("id")
        val id: String = "0",

        /**
         * The URL of an attached Status.
         */
        @SerialName("url")
        val url: String = ""
    )
}
