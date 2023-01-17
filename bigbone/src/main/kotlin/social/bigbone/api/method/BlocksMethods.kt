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
class BlocksMethods(private val client: MastodonClient) {

    //  GET /api/v1/blocks
    @JvmOverloads
    fun getBlocks(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/blocks",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }
}
