package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a revision of a status that has been edited.
 * @see <a href="https://docs.joinmastodon.org/entities/StatusEdit/">Mastodon API StatusEdit</a>
 */
data class StatusEdit(
    /**
     * The content of the status at this revision (HTML string).
     */
    @SerializedName("content")
    val content: String = "",

    /**
     * The content of the subject or content warning at this revision (HTML string).
     */
    @SerializedName("spoiler_text")
    val spoilerText: String = "",

    /**
     * Whether the status was marked sensitive at this revision.
     */
    @SerializedName("sensitive")
    val sensitive: Boolean = false,

    /**
     * The timestamp of when the revision was published (ISO 8601 Datetime string).
     */
    @SerializedName("created_at")
    val createdAt: String = "",

    /**
     * The account that published this revision.
     */
    @SerializedName("account")
    val account: Account? = null,

    /**
     * The current state of the poll options at this revision. Note that edits changing the poll options will be
     * collapsed together into one edit, since this action resets the poll.
     */
    @SerializedName("poll")
    val poll: PollRevision? = null,

    /**
     * Media attachments at this revision.
     */
    @SerializedName("media_attachments")
    val mediaAttachments: List<MediaAttachment> = emptyList(),

    /**
     * Any custom emoji that are used in the current revision.
     */
    @SerializedName("emojis")
    val emojis: List<CustomEmoji> = emptyList()
) {

    /**
     * The current state of the poll options at this revision.
     */
    data class PollRevision(
        @SerializedName("options")
        val options: List<PollOption> = emptyList(),
    )

    /**
     * A single poll option at some revision.
     */
    data class PollOption(
        /**
         * The text for a poll option.
         */
        @SerializedName("title")
        val title: String = ""
    )
}
