package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

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
    val textUrl: String? = null
) {
    enum class Type(val value: String) {
        Image("image"),
        Video("video"),
        Gifv("gifv")
    }
}
