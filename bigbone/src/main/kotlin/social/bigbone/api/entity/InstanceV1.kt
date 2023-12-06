package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the software instance of Mastodon running on this domain.
 * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class InstanceV1(
    /**
     * The domain name of the instance.
     */
    @SerialName("uri")
    val uri: String = "",

    /**
     * The title of the website.
     */
    @SerialName("title")
    val title: String = "",

    /**
     * A short, plain-text description defined by the admin.
     */
    @SerialName("short_description")
    val shortDescription: String = "",

    /**
     * An HTML-permitted description of the Mastodon site.
     */
    @SerialName("description")
    val description: String = "",

    /**
     * An email that may be contacted for any inquiries.
     */
    @SerialName("email")
    val email: String = "",

    /**
     * The version of Mastodon installed on the instance.
     */
    @SerialName("version")
    val version: String = "",

    /**
     * URLs of interest for clients apps.
     */
    @SerialName("urls")
    val urls: Urls? = null,

    /**
     * Statistics about how much information the instance contains.
     */
    @SerialName("stats")
    val stats: Stats? = null,

    /**
     * Banner image for the website.
     */
    @SerialName("thumbnail")
    val thumbnail: String = "",

    /**
     * Primary languages of the website and its staff.
     */
    @SerialName("languages")
    val languages: List<String> = emptyList(),

    /**
     * Whether registrations are enabled.
     */
    @SerialName("registrations")
    val registrations: Boolean = false,

    /**
     * Whether registrations require moderator approval.
     */
    @SerialName("approval_required")
    val approvalRequired: Boolean = false,

    /**
     * Whether invites are enabled.
     */
    @SerialName("invites_enabled")
    val invitesEnabled: Boolean = false,

    /**
     * Configured values and limits for this website.
     */
    @SerialName("configuration")
    val configuration: Configuration? = null,

    /**
     * A user that can be contacted, as an alternative to email.
     */
    @SerialName("contact_account")
    val contactAccount: Account? = null,

    /**
     * An itemized list of rules for this website.
     */
    @SerialName("rules")
    val rules: List<Rule>? = null
) {
    /**
     * URLs of interest for clients apps.
     * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
     */
    @Serializable
    data class Urls(
        /**
         * The Websockets URL for connecting to the streaming API.
         */
        @SerialName("streaming_api")
        val streamingApi: String = ""
    )

    /**
     * Statistics about how much information the instance contains.
     * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
     */
    @Serializable
    data class Stats(
        /**
         * Total users on this instance.
         */
        @SerialName("user_count")
        val userCount: Long = 0,

        /**
         * Total statuses on this instance.
         */
        @SerialName("status_count")
        val statusCount: Long = 0,

        /**
         * Total domains discovered by this instance.
         */
        @SerialName("domain_count")
        val domainCount: Long = 0
    )

    /**
     * Configured values and limits for this website.
     * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
     */
    @Serializable
    data class Configuration(
        /**
         * Limits related to accounts.
         */
        @SerialName("accounts")
        val accounts: Accounts? = null,

        /**
         * Limits related to authoring statuses.
         */
        @SerialName("statuses")
        val statuses: Statuses? = null,

        /**
         * Hints for which attachments will be accepted.
         */
        @SerialName("media_attachments")
        val mediaAttachments: MediaAttachments? = null,

        /**
         * Limits related to polls.
         */
        @SerialName("polls")
        val polls: Polls? = null
    ) {
        /**
         * Limits related to accounts.
         * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
         */
        @Serializable
        data class Accounts(
            /**
             * The maximum number of featured tags allowed for each account.
             */
            @SerialName("max_featured_tags")
            val maxFeaturedTags: Int = 0
        )

        /**
         * Limits related to authoring statuses.
         * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
         */
        @Serializable
        data class Statuses(
            /**
             * The maximum number of allowed characters per status.
             */
            @SerialName("max_characters")
            val maxCharacters: Int = 0,

            /**
             * The maximum number of media attachments that can be added to a status.
             */
            @SerialName("max_media_attachments")
            val maxMediaAttachments: Int = 0,

            /**
             * Each URL in a status will be assumed to be exactly this many characters.
             */
            @SerialName("characters_reserved_per_url")
            val charactersReservedPerUrl: Int = 0
        )

        /**
         * Hints for which attachments will be accepted.
         * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
         */
        @Serializable
        data class MediaAttachments(
            /**
             * Contains MIME types that can be uploaded.
             */
            @SerialName("supported_mime_types")
            val supportedMimeTypes: List<String> = emptyList(),

            /**
             * The maximum size of any uploaded image, in bytes.
             */
            @SerialName("image_size_limit")
            val imageSizeLimit: Int = 0,

            /**
             * The maximum number of pixels (width times height) for image uploads.
             */
            @SerialName("image_matrix_limit")
            val imageMatrixLimit: Int = 0,

            /**
             * The maximum size of any uploaded video, in bytes.
             */
            @SerialName("video_size_limit")
            val videoSizeLimit: Int = 0,

            /**
             * The maximum frame rate for any uploaded video.
             */
            @SerialName("video_frame_rate_limit")
            val videoFrameRateLimit: Int = 0,

            /**
             * The maximum number of pixels (width times height) for video uploads.
             */
            @SerialName("video_matrix_limit")
            val videoMatrixLimit: Int = 0
        )

        /**
         * Limits related to polls.
         * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API V1::Instance</a>
         */
        @Serializable
        data class Polls(
            /**
             * Each poll is allowed to have up to this many options.
             */
            @SerialName("max_options")
            val maxOptions: Int = 0,

            /**
             * Each poll option is allowed to have this many characters.
             */
            @SerialName("max_characters_per_option")
            val maxCharactersPerOption: Int = 0,

            /**
             * The shortest allowed poll duration, in seconds.
             */
            @SerialName("min_expiration")
            val minExpiration: Int = 0,

            /**
             * The longest allowed poll duration, in seconds.
             */
            @SerialName("max_expiration")
            val maxExpiration: Int = 0
        )
    }
}
