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
        return MastodonRequest<Pageable<MastodonList>>(
            {
                client.get(
                    "lists"
                )
            },
            {
                client.getSerializer().fromJson(it, MastodonList::class.java)
            }
        ).toPageable()
    }

    // GET /api/v1/timelines/list/:list_id
    @Throws(BigboneRequestException::class)
    fun getListTimeLine(listID: Long, range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return MastodonRequest<Pageable<Status>>(
            {
                client.get(
                    "timelines/list/$listID",
                    range.toParameter()
                )
            },
            {
                client.getSerializer().fromJson(it, Status::class.java)
            }
        ).toPageable()
    }
}
