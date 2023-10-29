package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime

/**
 * Represents a revision of a status that has been edited.
 * @see <a href="https://docs.joinmastodon.org/entities/StatusEdit/">Mastodon API StatusEdit</a>
 */
@Serializable
data class StatusEdit(
    /**
     * The content of the status at this revision (HTML string).
     */
    @SerialName("content")
    val content: String = "",

    /**
     * The content of the subject or content warning at this revision (HTML string).
     */
    @SerialName("spoiler_text")
    val spoilerText: String = "",

    /**
     * Whether the status was marked sensitive at this revision.
     */
    @SerialName("sensitive")
    val sensitive: Boolean = false,

    /**
     * The timestamp of when the revision was published (ISO 8601 Datetime string).
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.Unavailable,

    /**
     * The account that published this revision.
     */
    @SerialName("account")
    val account: Account? = null,

    /**
     * The current state of the poll options at this revision. Note that edits changing the poll options will be
     * collapsed together into one edit, since this action resets the poll.
     */
    @SerialName("poll")
    val poll: PollRevision? = null,

    /**
     * Media attachments at this revision.
     */
    @SerialName("media_attachments")
    val mediaAttachments: List<MediaAttachment> = emptyList(),

    /**
     * Any custom emoji that are used in the current revision.
     */
    @SerialName("emojis")
    val emojis: List<CustomEmoji> = emptyList()
) {

    /**
     * The current state of the poll options at this revision.
     */
    @Serializable
    data class PollRevision(
        @SerialName("options")
        val options: List<PollOption> = emptyList()
    )

    /**
     * A single poll option at some revision.
     */
    @Serializable
    data class PollOption(
        /**
         * The text for a poll option.
         */
        @SerialName("title")
        val title: String = ""
    )
}
