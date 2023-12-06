package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.entity.PreviewCard.CardType
import social.bigbone.api.entity.data.History

/**
 * Represents a rich preview card that is generated using OpenGraph tags from a URL.
 * @see <a href="https://docs.joinmastodon.org/entities/PreviewCard/#trends-link">Mastodon API PreviewCard/Trends::Link</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class TrendsLink(
    /**
     * Location of linked resource.
     */
    @SerialName("url")
    val url: String? = null,

    /**
     * Title of linked resource.
     */
    @SerialName("title")
    val title: String? = null,

    /**
     * Description of preview.
     */
    @SerialName("description")
    val description: String? = null,

    /**
     * The type of the preview card.
     * @see CardType
     */
    @SerialName("type")
    val type: CardType = CardType.LINK,

    /**
     * The author of the original resource.
     */
    @SerialName("author_name")
    val authorName: String? = null,

    /**
     * A link to the author of the original resource.
     */
    @SerialName("author_url")
    val authorUrl: String? = null,

    /**
     * The provider of the original resource.
     */
    @SerialName("provider_name")
    val providerName: String? = null,

    /**
     * A link to the provider of the original resource.
     */
    @SerialName("provider_url")
    val providerUrl: String? = null,

    /**
     * HTML to be used for generating the preview card.
     */
    @SerialName("html")
    val html: String? = null,

    /**
     * Width of preview, in pixels.
     */
    @SerialName("width")
    val width: Int? = null,

    /**
     * Height of preview, in pixels.
     */
    @SerialName("height")
    val height: Int? = null,

    /**
     * Preview thumbnail.
     */
    @SerialName("image")
    val image: String? = null,

    /**
     * Used for photo embeds, instead of custom html.
     */
    @SerialName("embed_url")
    val embedUrl: String? = null,

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
    val history: List<History>? = null
)
