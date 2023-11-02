package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.OEmbedMetadata
import social.bigbone.api.method.OEmbedMethods

/**
 * Reactive implementation of [OEmbedMethods]. For generating OEmbed previews.
 * @see <a href="https://docs.joinmastodon.org/methods/oembed/">Mastodon oembed API methods</a>
 */
class RxOEmbedMethods(private val mastodonClient: MastodonClient) {

    private val oEmbedMethods = OEmbedMethods(mastodonClient)

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
    ): Single<OEmbedMetadata> = Single.fromCallable {
        oEmbedMethods.getOEmbedInfoAsJson(
            urlOfStatus = urlOfStatus,
            maxWidth = maxWidth,
            maxHeight = maxHeight
        ).execute()
    }
}
