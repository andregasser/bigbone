package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/follow_requests" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/">Mastodon follow_requests API methods</a>
 */
class FollowRequestsMethods(private val client: MastodonClient) {
    // GET /api/v1/follow_requests
    @JvmOverloads
    fun getFollowRequests(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/follow_requests",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    //  POST /api/v1/follow_requests/:id/authorize
    @Throws(BigBoneRequestException::class)
    fun authorizeFollowRequest(accountId: String) {
        client.performAction(
            endpoint = "api/v1/follow_requests/$accountId/authorize",
            method = MastodonClient.Method.POST
        )
    }

    //  POST /api/v1/follow_requests/:id/reject
    @Throws(BigBoneRequestException::class)
    fun rejectFollowRequest(accountId: String) {
        client.performAction(
            endpoint = "api/v1/follow_requests/$accountId/reject",
            method = MastodonClient.Method.POST
        )
    }
}
