package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.exception.BigboneRequestException

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
class Timelines(private val client: MastodonClient) {
    //  GET /api/v1/timelines/home
    @JvmOverloads
    @Throws(BigboneRequestException::class)
    fun getHome(range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "api/v1/timelines/home",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }
}
