package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#favourites
 */
class Favourites(private val client: MastodonClient) {

    //  GET /api/v1/favourites
    @JvmOverloads
    fun getFavourites(range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get("api/v1/favourites", range.toParameter())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }
}
