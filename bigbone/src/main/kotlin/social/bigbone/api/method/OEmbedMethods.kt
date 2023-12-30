package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.OEmbedMetadata

/**
 * For generating OEmbed previews.
 * @see <a href="https://docs.joinmastodon.org/methods/oembed/">Mastodon oembed API methods</a>
 */
class OEmbedMethods(private val mastodonClient: MastodonClient) {

    private val endpoint = "api/oembed"

    /**
     * Get oEmbed info as JSON.
     * @see <a href="https://docs.joinmastodon.org/methods/oembed/#get">Mastodon API documentation: methods/oembed/#get</a>
     * @param urlOfStatus URL of a status for which to return oEmbed info.
     * @param maxWidth Width of the iframe. Defaults to 400.
     * @param maxHeight Height of the iframe. Defaults to null.
     */
    @JvmOverloads
    fun getOEmbedInfoAsJson(
        urlOfStatus: String,
        maxWidth: Int = 400,
        maxHeight: Int? = null
    ): MastodonRequest<OEmbedMetadata> {
        return mastodonClient.getMastodonRequest<OEmbedMetadata>(
            endpoint = endpoint,
            method = MastodonClient.Method.GET,
            parameters = Parameters().apply {
                append("url", urlOfStatus)
                append("maxwidth", maxWidth)
                maxHeight?.let { append("maxheight", it) }
            }
        )
    }
}
