package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName
import social.bigbone.api.entity.data.History

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
     * The type of the preview card.
     * @see CardType
     */
    @SerializedName("type")
    val type: String = CardType.Link.value,

    /**
     * The author of the original resource.
     */
    @SerializedName("author_name")
    val authorName: String = "",

    /**
     * A link to the author of the original resource.
     */
    @SerializedName("author_url")
    val authorUrl: String = "",

    /**
     * The provider of the original resource.
     */
    @SerializedName("provider_name")
    val providerName: String = "",

    /**
     * A link to the provider of the original resource.
     */
    @SerializedName("provider_url")
    val providerUrl: String = "",

    /**
     * HTML to be used for generating the preview card.
     */
    @SerializedName("html")
    val html: String = "",

    /**
     * Width of preview, in pixels.
     */
    @SerializedName("width")
    val width: Int = 0,

    /**
     * Height of preview, in pixels.
     */
    @SerializedName("height")
    val height: Int = 0,

    /**
     * Preview thumbnail.
     */
    @SerializedName("image")
    val image: String? = null,

    /**
     * Used for photo embeds, instead of custom html.
     */
    @SerializedName("method_url")
    val methodUrl: String = "",

    /**
     * A hash computed by the BlurHash algorithm,
     * for generating colorful preview thumbnails
     * when media has not been downloaded yet.
     */
    @SerializedName("blurhash")
    val blurhash: String? = null,

    /**
     * Usage statistics for given days (typically the past week).
     */
    @SerializedName("history")
    val history: List<History> = emptyList()
) {
    /**
     * Specifies the type of the preview card.
     */
    enum class CardType(val value: String) {
        Link("link"),
        Photo("photo"),
        Video("video"),
        Rich("iframe")
    }
}
