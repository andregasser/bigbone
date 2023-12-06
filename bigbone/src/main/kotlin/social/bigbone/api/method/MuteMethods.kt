package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account

/**
 * Allows access to API methods with endpoints having an "api/vX/mutes" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/mutes/">Mastodon mutes API methods</a>
 */
class MuteMethods(private val client: MastodonClient) {
    /**
     * Accounts the user has muted.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/mutes/#get">Mastodon API documentation: methods/mutes/#get</a>
     */
    @JvmOverloads
    fun getMutes(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/mutes",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }
}
