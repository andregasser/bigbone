package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.exception.BigBoneRequestException

class FollowRequestsMethods(private val client: MastodonClient) {
    // GET /api/v1/follow_requests
    @JvmOverloads
    fun getFollowRequests(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/follow_requests",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
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
