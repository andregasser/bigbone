package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a status posted by an account.
 * @see <a href="https://docs.joinmastodon.org/entities/Status/">Mastodon API Status</a>
 */
data class Status(
    /**
     * ID of the status in the database.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * URI of the status used for federation.
     */
    @SerializedName("uri")
    val uri: String = "",

    /**
     * The date when this status was created (ISO 8601 Datetime).
     */
    @SerializedName("created_at")
    val createdAt: String = "",

    /**
     * The account that authored this status.
     */
    @SerializedName("account")
    val account: Account? = null,

    /**
     * HTML-encoded status content.
     */
    @SerializedName("content")
    val content: String = "",

    /**
     * Visibility of this status.
     * @see Visibility
     */
    @SerializedName("visibility")
    val visibility: String = Visibility.Public.value,

    /**
     * Is this status marked as sensitive content?
     */
    @SerializedName("sensitive")
    val isSensitive: Boolean = false,

    /**
     * Subject or summary line, below which status content is collapsed until expanded.
     */
    @SerializedName("spoiler_text")
    val spoilerText: String = "",

    /**
     * Media that is attached to this status.
     */
    @SerializedName("media_attachments")
    val mediaAttachments: List<MediaAttachment> = emptyList(),

    /**
     * The application used to post this status (optional).
     */
    @SerializedName("application")
    val application: Application? = null,

    /**
     * Mentions of users within the status content.
     */
    @SerializedName("mentions")
    val mentions: List<Mention> = emptyList(),

    /**
     * Hashtags used within the status content.
     */
    @SerializedName("tags")
    val tags: List<Tag> = emptyList(),

    /**
     * Custom emoji to be used when rendering status content.
     */
    @SerializedName("emojis")
    val emojis: List<CustomEmoji> = emptyList(),

    /**
     * How many boosts this status has received.
     */
    @SerializedName("reblogs_count")
    val reblogsCount: Long = 0,

    /**
     * How many favourites this status has received.
     */
    @SerializedName("favourites_count")
    val favouritesCount: Long = 0,

    /**
     * How many replies this status has received.
     */
    @SerializedName("replies_count")
    val repliesCount: Long = 0,

    /**
     * A link to the status’s HTML representation.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * ID of the status being replied to.
     */
    @SerializedName("in_reply_to_id")
    val inReplyToId: String? = null,

    /**
     * ID of the account that authored the status being replied to.
     */
    @SerializedName("in_reply_to_account_id")
    val inReplyToAccountId: String? = null,

    /**
     * The status being reblogged.
     */
    @SerializedName("reblog")
    val reblog: Status? = null,

    /**
     * The poll attached to the status.
     */
    @SerializedName("poll")
    val poll: Poll? = null,

    /**
     * Preview card for links included within status content.
     */
    @SerializedName("card")
    val card: PreviewCard? = null,

    /**
     * Primary language of this status (ISO 639 Part 1 two-letter language code).
     */
    @SerializedName("language")
    val language: String? = null,

    /**
     * Plain-text source of a status. Returned instead of content when status is deleted, so the user may redraft from
     * the source text without the client having to reverse-engineer the original text from the HTML content.
     */
    @SerializedName("text")
    val text: String? = null,

    /**
     * Timestamp of when the status was last edited (ISO 8601 Datetime).
     */
    @SerializedName("edited_at")
    val editedAt: String? = null,

    /**
     * If the current token has an authorized user: Have you favourited this status? (optional)
     */
    @SerializedName("favourited")
    val isFavourited: Boolean = false,

    /**
     * If the current token has an authorized user: Have you boosted this status? (optional)
     */
    @SerializedName("reblogged")
    val isReblogged: Boolean = false,

    /**
     * If the current token has an authorized user: Have you muted notifications for this status’s conversation? (optional)
     */
    @SerializedName("muted")
    val isMuted: Boolean = false,

    /**
     * If the current token has an authorized user: Have you bookmarked this status? (optional)
     */
    @SerializedName("bookmarked")
    val isBookmarked: Boolean = false,

    /**
     * If the current token has an authorized user: Have you pinned this status? A value of false does not imply that
     * this status is in fact pinnable.
     */
    @SerializedName("pinned")
    val isPinned: Boolean = false,

    /**
     * If the current token has an authorized user: The filter and keywords that matched this status. (optional)
     */
    @SerializedName("filtered")
    val filtered: List<FilterResult>? = null
) {
    /**
     * Specifies the visibility of the status post.
     */
    enum class Visibility(val value: String) {
        Public("public"),
        Unlisted("unlisted"),
        Private("private"),
        Direct("direct")
    }

    /**
     * Mentions of users within the status content.
     * @see <a href="https://docs.joinmastodon.org/entities/Status/#Mention">Mastodon API Status::Mention</a>
     */
    data class Mention(
        /**
         * The location of the mentioned user’s profile.
         */
        @SerializedName("url")
        val url: String = "",

        /**
         * The username of the mentioned user.
         */
        @SerializedName("username")
        val username: String = "",

        /**
         * The webfinger acct: URI of the mentioned user. Equivalent to username for local users, or username@domain for remote users.
         */
        @SerializedName("acct")
        val acct: String = "",

        /**
         * The account ID of the mentioned user.
         */
        @SerializedName("id")
        val id: String = "0"
    )
}
