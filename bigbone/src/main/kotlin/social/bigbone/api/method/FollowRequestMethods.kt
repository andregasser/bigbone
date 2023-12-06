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
class FollowRequestMethods(private val client: MastodonClient) {
    /**
     * View pending follow requests.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/#get">Mastodon API documentation: methods/follow_requests/#get</a>
     */
    @JvmOverloads
    fun getFollowRequests(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/follow_requests",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Accept follow request.
     * @param accountId ID of the account whose follow request should be accepted.
     * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/#accept">Mastodon API documentation: methods/follow_requests/#accept</a>
     */
    @Throws(BigBoneRequestException::class)
    fun authorizeFollowRequest(accountId: String) {
        client.performAction(
            endpoint = "api/v1/follow_requests/$accountId/authorize",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Reject follow request.
     * @param accountId ID of the account whose follow request should be rejected.
     * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/#reject">Mastodon API documentation: methods/follow_requests/#reject</a>
     */
    @Throws(BigBoneRequestException::class)
    fun rejectFollowRequest(accountId: String) {
        client.performAction(
            endpoint = "api/v1/follow_requests/$accountId/reject",
            method = MastodonClient.Method.POST
        )
    }
}
