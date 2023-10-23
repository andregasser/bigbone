package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an announcement set by an administrator.
 * @see <a href="https://docs.joinmastodon.org/entities/Announcement/"> Mastadon API Announcment </a>
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
    val startsAt: String? = null,

    /**
     * When the announcement will end.
     */
    @SerialName("ends_at")
    val endsAt: String? = null,

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
    val publishedAt: String = "",

    /**
     * When the announcement will end.
     */
    @SerialName("updated_at")
    val updatedAt: String? = null,

    /**
     * Whether the announcement has been read by the current user.
     */
    @SerialName("read")
    val read: Boolean = true,

    /**
     * Accounts mentioned in the announcement text.
     */
    @SerialName("mentions")
    val mentions: List<Account> = emptyList(),

    /**
     * Statuses linked in the announcement text.
     */
    @SerialName("statuses")
    val statuses: List<Status> = emptyList(),

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
)
