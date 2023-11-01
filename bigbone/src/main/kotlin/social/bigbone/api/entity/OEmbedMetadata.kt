package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.method.OEmbedMethods

/**
 * oEmbed metadata returned by [OEmbedMethods].
 * @see <a href="https://oembed.com/#section2.3">OEmbed response parameters documentation</a>
 */
@Serializable
data class OEmbedMetadata(
    /**
     * The resource type.
     * It’s unclear from <a href="https://docs.joinmastodon.org/methods/oembed/#get">Mastodon’s documentation</a>
     * if this will always be equal to "rich" or if that was only an example.
     */
    @SerialName("type")
    val type: String = "",

    /**
     * The oEmbed version number. Must be 1.0.
     */
    @SerialName("version")
    val version: String = "1.0",

    /**
     * A text title, describing this resource.
     */
    @SerialName("title")
    val title: String? = "",

    /**
     * The name of the author/owner of the resource.
     */
    @SerialName("author_name")
    val authorName: String? = "",

    /**
     * A URL for the other/owner of the resource.
     */
    @SerialName("author_url")
    val authorUrl: String? = "",

    /**
     * The name of the resource provider.
     */
    @SerialName("provider_name")
    val providerName: String? = "",

    /**
     * The URL of the resource provider.
     */
    @SerialName("provider_url")
    val providerUrl: String? = "",

    /**
     * The suggested cache lifetime for this resource, in seconds.
     * Consumers may choose to use this value or not.
     */
    @SerialName("cache_age")
    val cacheAge: Int? = null,

    /**
     * The HTML required to display the resource.
     * The HTML should have no padding or margins.
     * Consumers may wish to load the HTML in an off-domain iframe to avoid XSS vulnerabilities.
     * The markup should be valid XHTML 1.0 Basic.
     */
    @SerialName("html")
    val html: String? = "",

    /**
     * The width in pixels required to display the HTML.
     */
    @SerialName("width")
    val width: Int? = null,

    /**
     * The height in pixels required to display the HTML.
     */
    @SerialName("height")
    val height: Int? = null
)
