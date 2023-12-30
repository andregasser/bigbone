package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account

/**
 * Allows access to API methods with endpoints having an "api/vX/blocks" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/blocks/">Mastodon blocks API methods</a>
 */
class BlockMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/blocks"

    /**
     * View your blocks. Blocking and unblocking is achieved via accounts methods.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/blocks/#get">Mastodon API documentation: methods/blocks/#get</a>
     */
    @JvmOverloads
    fun getBlocks(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }
}
