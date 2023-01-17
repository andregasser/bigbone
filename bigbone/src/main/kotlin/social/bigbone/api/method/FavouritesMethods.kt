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
class FavouritesMethods(private val client: MastodonClient) {

    //  GET /api/v1/favourites
    @JvmOverloads
    fun getFavourites(range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/favourites",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }
}
