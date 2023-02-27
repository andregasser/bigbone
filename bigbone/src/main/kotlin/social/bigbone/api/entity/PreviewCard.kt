package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a rich preview card that is generated using OpenGraph tags from a URL.
 * @see <a href="https://docs.joinmastodon.org/entities/PreviewCard/">Mastodon API PreviewCard</a>
 */
data class PreviewCard(
    /**
     * Location of linked resource.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * Title of linked resource.
     */
    @SerializedName("title")
    val title: String = "",

    /**
     * Description of preview.
     */
    @SerializedName("description")
    val description: String = "",

    /**
     * Preview thumbnail.
     */
    @SerializedName("image")
    val image: String? = null
)
