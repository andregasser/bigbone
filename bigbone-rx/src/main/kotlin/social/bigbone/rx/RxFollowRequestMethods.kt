package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Relationship
import social.bigbone.api.method.FollowRequestMethods

/**
 * Reactive implementation of [FollowRequestMethods].
 * Allows access to API methods with endpoints having an "api/vX/follow_requests" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/">Mastodon follow_requests API methods</a>
 */
class RxFollowRequestMethods(client: MastodonClient) {
    private val followRequestMethods = FollowRequestMethods(client)

    /**
     * View pending follow requests.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/#get">Mastodon API documentation: methods/follow_requests/#get</a>
     */
    @JvmOverloads
    fun getFollowRequests(range: Range = Range()): Single<Pageable<Account>> = Single.fromCallable { followRequestMethods.getFollowRequests(range).execute() }

    /**
     * Accept follow request.
     * @param accountId ID of the account whose follow request should be accepted.
     * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/#accept">Mastodon API documentation: methods/follow_requests/#accept</a>
     */
    fun authorizeFollowRequest(accountId: String): Single<Relationship> =
        Single.fromCallable { followRequestMethods.authorizeFollowRequest(accountId).execute() }

    /**
     * Reject follow request.
     * @param accountId ID of the account whose follow request should be rejected.
     * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/#reject">Mastodon API documentation: methods/follow_requests/#reject</a>
     */
    fun rejectFollowRequest(accountId: String): Single<Relationship> = Single.fromCallable { followRequestMethods.rejectFollowRequest(accountId).execute() }
}
