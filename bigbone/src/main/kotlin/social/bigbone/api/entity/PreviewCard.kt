package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.entity.PreviewCard.CardType
import social.bigbone.api.entity.data.History

/**
 * Represents a rich preview card that is generated using OpenGraph tags from a URL.
 * @see <a href="https://docs.joinmastodon.org/entities/PreviewCard/">Mastodon API PreviewCard</a>
 */
@Serializable
data class PreviewCard(
    /**
     * Location of linked resource.
     */
    @SerialName("url")
    val url: String = "",

    /**
     * Title of linked resource.
     */
    @SerialName("title")
    val title: String = "",

    /**
     * Description of preview.
     */
    @SerialName("description")
    val description: String = "",

    /**
     * The type of the preview card.
     * @see CardType
     */
    @SerialName("type")
    val type: String = CardType.Link.value,

    /**
     * The author of the original resource.
     */
    @SerialName("author_name")
    val authorName: String = "",

    /**
     * A link to the author of the original resource.
     */
    @SerialName("author_url")
    val authorUrl: String = "",

    /**
     * The provider of the original resource.
     */
    @SerialName("provider_name")
    val providerName: String = "",

    /**
     * A link to the provider of the original resource.
     */
    @SerialName("provider_url")
    val providerUrl: String = "",

    /**
     * HTML to be used for generating the preview card.
     */
    @SerialName("html")
    val html: String = "",

    /**
     * Width of preview, in pixels.
     */
    @SerialName("width")
    val width: Int = 0,

    /**
     * Height of preview, in pixels.
     */
    @SerialName("height")
    val height: Int = 0,

    /**
     * Preview thumbnail.
     */
    @SerialName("image")
    val image: String? = null,

    /**
     * Used for photo embeds, instead of custom html.
     */
    @SerialName("method_url")
    val methodUrl: String = "",

    /**
     * A hash computed by the BlurHash algorithm,
     * for generating colorful preview thumbnails
     * when media has not been downloaded yet.
     */
    @SerialName("blurhash")
    val blurhash: String? = null,

    /**
     * Usage statistics for given days (typically the past week).
     */
    @SerialName("history")
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
