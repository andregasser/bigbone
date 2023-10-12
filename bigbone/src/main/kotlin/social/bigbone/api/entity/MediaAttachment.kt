package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName
import social.bigbone.api.entity.data.Focus

/**
 * Represents a file or media attachment that can be added to a status.
 * @see <a href="https://docs.joinmastodon.org/entities/MediaAttachment/">Mastodon API MediaAttachment</a>
 */
data class MediaAttachment(
    /**
     * The ID of the attachment in the database.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * The type of the attachment.
     */
    @SerializedName("type")
    val type: String = Type.Image.value,

    /**
     * The location of the original full-size attachment.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * The location of the full-size original attachment on the remote website.
     */
    @SerializedName("remote_url")
    val remoteUrl: String? = null,

    /**
     * The location of a scaled-down preview of the attachment.
     */
    @SerializedName("preview_url")
    val previewUrl: String = "",

    /**
     * A shorter URL for the attachment.
     */
    @SerializedName("text_url")
    val textUrl: String? = null,

    /**
     * Metadata returned by Paperclip.
     */
    @SerializedName("meta")
    val meta: Meta? = null,

    /**
     * Alternate text that describes what is in the media attachment, to be used for the visually impaired or when media attachments do not load.
     */
    @SerializedName("description")
    val description: String? = null,

    /**
     * A hash computed by the BlurHash algorithm, for generating colorful preview thumbnails when media has not been downloaded yet.
     */
    @SerializedName("blurhash")
    val blurhash: String? = null
) {
    /**
     * Metadata returned by Paperclip.
     */
    data class Meta(
        /**
         * Contains the coordinates to be used for smart thumbnail cropping.
         */
        @SerializedName("focus")
        val focus: Focus? = null
    )

    /**
     * The available media types.
     */
    enum class Type(val value: String) {
        Audio("audio"),
        Image("image"),
        Video("video"),
        Gifv("gifv"),
        Unknown("unknown")
    }
}
