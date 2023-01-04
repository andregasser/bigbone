package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.MastodonList
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigboneRequestException

class MastodonLists(private val client: MastodonClient) {

    // GET /api/v1/lists
    fun getLists(): MastodonRequest<Pageable<MastodonList>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/lists",
            method = MastodonClient.Method.GET
        )
    }

    // GET /api/v1/timelines/list/:list_id
    @Throws(BigboneRequestException::class)
    fun getListTimeLine(listID: String, range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/timelines/list/$listID",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }
}
