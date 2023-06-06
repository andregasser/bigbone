package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents the software instance of Mastodon running on this domain.
 * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
 */
data class Instance(
    /**
     * The domain name of the instance.
     */
    @SerializedName("uri")
    val uri: String = "",

    /**
     * The title of the website.
     */
    @SerializedName("title")
    val title: String = "",

    /**
     * A short, plain-text description defined by the admin.
     */
    @SerializedName("short_description")
    val shortDescription: String = "",

    /**
     * An HTML-permitted description of the Mastodon site.
     */
    @SerializedName("description")
    val description: String = "",

    /**
     * An email that may be contacted for any inquiries.
     */
    @SerializedName("email")
    val email: String = "",

    /**
     * The version of Mastodon installed on the instance.
     */
    @SerializedName("version")
    val version: String = "",

    /**
     * URLs of interest for clients apps.
     */
    @SerializedName("urls")
    val urls: Urls? = null,

    /**
     * Statistics about how much information the instance contains.
     */
    @SerializedName("stats")
    val stats: Stats? = null,

    /**
     * Banner image for the website.
     */
    @SerializedName("thumbnail")
    val thumbnail: String = "",

    /**
     * Primary languages of the website and its staff.
     */
    @SerializedName("languages")
    val languages: List<String> = emptyList(),

    /**
     * Whether registrations are enabled.
     */
    @SerializedName("registrations")
    val registrations: Boolean = false,

    /**
     * Whether registrations require moderator approval.
     */
    @SerializedName("approval_required")
    val approvalRequired: Boolean = false,

    /**
     * Whether invites are enabled.
     */
    @SerializedName("invites_enabled")
    val invitesEnabled: Boolean = false,

    /**
     * Configured values and limits for this website.
     */
    @SerializedName("configuration")
    val configuration: Configuration? = null,

    /**
     * A user that can be contacted, as an alternative to email.
     */
    @SerializedName("contact_account")
    val contactAccount: Account? = null,

    /**
     * An itemized list of rules for this website.
     */
    @SerializedName("rules")
    val rules: List<Rule>? = null
) {
    /**
     * URLs of interest for clients apps.
     * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
     */
    data class Urls(
        /**
         * The Websockets URL for connecting to the streaming API.
         */
        @SerializedName("streaming_api")
        val streamingApi: String = ""
    )

    /**
     * Statistics about how much information the instance contains.
     * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
     */
    data class Stats(
        /**
         * Total users on this instance.
         */
        @SerializedName("user_count")
        val userCount: Long = 0,

        /**
         * Total statuses on this instance.
         */
        @SerializedName("status_count")
        val statusCount: Long = 0,

        /**
         * Total domains discovered by this instance.
         */
        @SerializedName("domain_count")
        val domainCount: Long = 0
    )

    /**
     * Configured values and limits for this website.
     * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
     */
    data class Configuration(
        /**
         * Limits related to accounts.
         */
        @SerializedName("accounts")
        val accounts: Accounts? = null,

        /**
         * Limits related to authoring statuses.
         */
        @SerializedName("statuses")
        val statuses: Statuses? = null,

        /**
         * Hints for which attachments will be accepted.
         */
        @SerializedName("media_attachments")
        val mediaAttachments: MediaAttachments? = null,

        /**
         * Limits related to polls.
         */
        @SerializedName("polls")
        val polls: Polls? = null
    ) {
        /**
         * Limits related to accounts.
         * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
         */
        data class Accounts(
            /**
             * The maximum number of featured tags allowed for each account.
             */
            @SerializedName("max_featured_tags")
            val maxFeaturedTags: Int = 0
        )

        /**
         * Limits related to authoring statuses.
         * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
         */
        data class Statuses(
            /**
             * The maximum number of allowed characters per status.
             */
            @SerializedName("max_characters")
            val maxCharacters: Int = 0,

            /**
             * The maximum number of media attachments that can be added to a status.
             */
            @SerializedName("max_media_attachments")
            val maxMediaAttachments: Int = 0,

            /**
             * Each URL in a status will be assumed to be exactly this many characters.
             */
            @SerializedName("characters_reserved_per_url")
            val charactersReservedPerUrl: Int = 0
        )

        /**
         * Hints for which attachments will be accepted.
         * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
         */
        data class MediaAttachments(
            /**
             * Contains MIME types that can be uploaded.
             */
            @SerializedName("supported_mime_types")
            val supportedMimeTypes: List<String> = emptyList(),

            /**
             * The maximum size of any uploaded image, in bytes.
             */
            @SerializedName("image_size_limit")
            val imageSizeLimit: Int = 0,

            /**
             * The maximum number of pixels (width times height) for image uploads.
             */
            @SerializedName("image_matrix_limit")
            val imageMatrixLimit: Int = 0,

            /**
             * The maximum size of any uploaded video, in bytes.
             */
            @SerializedName("video_size_limit")
            val videoSizeLimit: Int = 0,

            /**
             * The maximum frame rate for any uploaded video.
             */
            @SerializedName("video_frame_rate_limit")
            val videoFrameRateLimit: Int = 0,

            /**
             * The maximum number of pixels (width times height) for video uploads.
             */
            @SerializedName("video_matrix_limit")
            val videoMatrixLimit: Int = 0
        )

        /**
         * Limits related to polls.
         * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
         */
        data class Polls(
            /**
             * Each poll is allowed to have up to this many options.
             */
            @SerializedName("max_options")
            val maxOptions: Int = 0,

            /**
             * Each poll option is allowed to have this many characters.
             */
            @SerializedName("max_characters_per_option")
            val maxCharactersPerOption: Int = 0,

            /**
             * The shortest allowed poll duration, in seconds.
             */
            @SerializedName("min_expiration")
            val minExpiration: Int = 0,

            /**
             * The longest allowed poll duration, in seconds.
             */
            @SerializedName("max_expiration")
            val maxExpiration: Int = 0
        )
    }
}
