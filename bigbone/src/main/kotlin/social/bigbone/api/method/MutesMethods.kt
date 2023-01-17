package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account

class MutesMethods(private val client: MastodonClient) {
    // GET /api/v1/mutes
    @JvmOverloads
    fun getMutes(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/mutes",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }
}
