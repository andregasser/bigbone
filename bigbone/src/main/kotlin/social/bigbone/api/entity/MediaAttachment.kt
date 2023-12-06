package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.entity.data.Focus

/**
 * Represents a file or media attachment that can be added to a status.
 * @see <a href="https://docs.joinmastodon.org/entities/MediaAttachment/">Mastodon API MediaAttachment</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class MediaAttachment(
    /**
     * The ID of the attachment in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The type of the attachment.
     */
    @SerialName("type")
    val type: MediaType = MediaType.IMAGE,

    /**
     * The location of the original full-size attachment.
     */
    @SerialName("url")
    val url: String = "",

    /**
     * The location of the full-size original attachment on the remote website.
     */
    @SerialName("remote_url")
    val remoteUrl: String? = null,

    /**
     * The location of a scaled-down preview of the attachment.
     */
    @SerialName("preview_url")
    val previewUrl: String = "",

    /**
     * A shorter URL for the attachment.
     */
    @SerialName("text_url")
    val textUrl: String? = null,

    /**
     * Metadata returned by Paperclip.
     */
    @SerialName("meta")
    val meta: Meta? = null,

    /**
     * Alternate text that describes what is in the media attachment, to be used for the visually impaired or when media attachments do not load.
     */
    @SerialName("description")
    val description: String? = null,

    /**
     * A hash computed by the BlurHash algorithm, for generating colorful preview thumbnails when media has not been downloaded yet.
     */
    @SerialName("blurhash")
    val blurhash: String? = null
) {
    /**
     * Metadata returned by Paperclip.
     */
    @Serializable
    data class Meta(
        /**
         * Contains the coordinates to be used for smart thumbnail cropping.
         */
        @SerialName("focus")
        val focus: Focus? = null
    )

    /**
     * The available media types.
     */
    @Serializable
    enum class MediaType {
        @SerialName("audio")
        AUDIO,

        @SerialName("image")
        IMAGE,

        @SerialName("video")
        VIDEO,

        @SerialName("gifv")
        GIFV,

        @SerialName("unknown")
        UNKNOWN
    }
}
