package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.entity.data.PollData
import social.bigbone.api.entity.data.Visibility

/**
 * Represents a status that will be published at a future scheduled date.
 * @see <a href="https://docs.joinmastodon.org/entities/ScheduledStatus/">Mastodon API ScheduledStatus</a>
 */
@Serializable
data class ScheduledStatus(
    /**
     * ID of the scheduled status in the database.
     */
    @SerialName("id")
    val id: String = "",

    /**
     * The timestamp for when the status will be posted (ISO 8601 Datetime).
     */
    @SerialName("scheduled_at")
    val scheduledAt: String = "",

    /**
     * The parameters that were used when scheduling the status, to be used when the status is posted.
     */
    @SerialName("params")
    val params: Parameters,

    /**
     * Media that will be attached when the status is posted.
     */
    @SerialName("media_attachments")
    val mediaAttachments: List<MediaAttachment> = emptyList()
) {
    /**
     * The parameters that were used when scheduling the status, to be used when the status is posted.
     * @see <a href="https://docs.joinmastodon.org/entities/ScheduledStatus/#params">Mastodon API ScheduledStatus params</a>
     */
    @Serializable
    data class Parameters(
        /**
         * Text to be used as status content.
         */
        @SerialName("text")
        val text: String = "",

        /**
         * Poll to be attached to the status.
         */
        @SerialName("poll")
        val poll: PollData? = null,

        /**
         * IDs of the MediaAttachments that will be attached to the status.
         */
        @SerialName("media_ids")
        val mediaIds: List<String>? = null,

        /**
         * Whether the status will be marked as sensitive.
         */
        @SerialName("sensitive")
        val sensitive: Boolean? = null,

        /**
         * The text of the content warning or summary for the status.
         */
        @SerialName("spoiler_text")
        val spoilerText: String = "",

        /**
         * The visibility that the status will have once it is posted.
         */
        @SerialName("visibility")
        val visibility: Visibility = Visibility.PUBLIC,

        /**
         * ID of the Status that will be replied to.
         */
        @SerialName("in_reply_to_id")
        val inReplyToId: String? = null,

        /**
         * The language that will be used for the status (ISO 639-1 two-letter language code).
         */
        @SerialName("language")
        val language: String? = null,

        /**
         * ID of the Application that posted the status.
         */
        @SerialName("application_id")
        val applicationId: Int = 0,

        /**
         * When the status will be scheduled. This will be null because the status is only scheduled once.
         */
        @SerialName("scheduled_at")
        val scheduledAt: String? = null,

        /**
         * Idempotency key to prevent duplicate statuses.
         */
        @SerialName("idempotency")
        val idempotency: String? = null,

        /**
         * Whether the status should be rate limited.
         */
        @SerialName("with_rate_limit")
        val withRateLimit: Boolean = false
    )
}
