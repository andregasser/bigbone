package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status

/**
 * Allows access to API methods with endpoints having an "api/vX/favourites" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/favourites/">Mastodon favourites API methods</a>
 */
class FavouriteMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/favourites"

    /**
     * View your favourites. Favouring and unfavouring is achieved via statuses methods.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/favourites/#get">Mastodon API documentation: methods/favourites/#get</a>
     */
    @JvmOverloads
    fun getFavourites(range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }
}
