package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a status that will be published at a future scheduled date.
 * @see <a href="https://docs.joinmastodon.org/entities/ScheduledStatus/">Mastodon API ScheduledStatus</a>
 */
data class ScheduledStatus(
    /**
     * ID of the scheduled status in the database.
     */
    @SerializedName("id")
    val id: String = "",

    /**
     * The timestamp for when the status will be posted (ISO 8601 Datetime).
     */
    @SerializedName("scheduled_at")
    val scheduledAt: String = "",

    /**
     * The parameters that were used when scheduling the status, to be used when the status is posted.
     */
    @SerializedName("params")
    val params: Parameters,

    /**
     * Media that will be attached when the status is posted.
     */
    @SerializedName("media_attachments")
    val mediaAttachments: List<MediaAttachment> = emptyList()
) {
    /**
     * The parameters that were used when scheduling the status, to be used when the status is posted.
     * @see <a href="https://docs.joinmastodon.org/entities/ScheduledStatus/#params">Mastodon API ScheduledStatus params</a>
     */
    data class Parameters(
        /**
         * Text to be used as status content.
         */
        @SerializedName("text")
        val text: String = "",

        /**
         * Poll to be attached to the status.
         */
        @SerializedName("poll")
        val poll: Poll? = null,

        /**
         * IDs of the MediaAttachments that will be attached to the status.
         */
        @SerializedName("media_ids")
        val mediaIds: List<String>? = null,

        /**
         * Whether the status will be marked as sensitive.
         */
        @SerializedName("sensitive")
        val sensitive: Boolean? = null,

        /**
         * The text of the content warning or summary for the status.
         */
        @SerializedName("spoiler_text")
        val spoilerText: String = "",

        /**
         * The visibility that the status will have once it is posted.
         */
        @SerializedName("visibility")
        val visibility: String = Status.Visibility.Public.value,

        /**
         * ID of the Status that will be replied to.
         */
        @SerializedName("in_reply_to_id")
        val inReplyToId: String? = null,

        /**
         * The language that will be used for the status (ISO 639-1 two-letter language code).
         */
        @SerializedName("language")
        val language: String? = null,

        /**
         * ID of the Application that posted the status.
         */
        @SerializedName("application_id")
        val applicationId: Int = 0,

        /**
         * When the status will be scheduled. This will be null because the status is only scheduled once.
         */
        @SerializedName("scheduled_at")
        val scheduledAt: String? = null,

        /**
         * Idempotency key to prevent duplicate statuses.
         */
        @SerializedName("idempotency")
        val idempotency: String? = null,

        /**
         * Whether the status should be rate limited.
         */
        @SerializedName("with_rate_limit")
        val withRateLimit: Boolean = false
    )

    /**
     * Poll to be attached to the status.
     */
    data class Poll(
        /**
         * The poll options to be used.
         * @see <a href="https://docs.joinmastodon.org/entities/ScheduledStatus/#params-poll">Mastodon API ScheduledStatus params-poll</a>
         */
        @SerializedName("options")
        val options: List<String> = emptyList(),

        /**
         * How many seconds the poll should last before closing (String cast from Integer).
         */
        @SerializedName("expires_in")
        val expiresIn: String = "",

        /**
         * Whether the poll allows multiple choices.
         */
        @SerializedName("multiple")
        val multiple: Boolean? = null,

        /**
         * Whether the poll should hide total votes until after voting has ended.
         */
        @SerializedName("hide_totals")
        val hideTotals: Boolean? = null
    )
}
