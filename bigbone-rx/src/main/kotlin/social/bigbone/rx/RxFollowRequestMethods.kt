package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.method.FollowRequestMethods

/**
 * Reactive implementation of [FollowRequestMethods].
 * Allows access to API methods with endpoints having an "api/vX/follow_requests" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/">Mastodon follow_requests API methods</a>
 */
class RxFollowRequestMethods(client: MastodonClient) {

    private val followRequestMethods = FollowRequestMethods(client)

    @JvmOverloads
    fun getFollowRequests(range: Range = Range()): Single<Pageable<Account>> = Single.fromCallable {
        followRequestMethods.getFollowRequests(range).execute()
    }

    fun authorizeFollowRequest(accountId: String): Completable = Completable.fromAction {
        followRequestMethods.authorizeFollowRequest(accountId)
    }

    fun rejectFollowRequest(accountId: String): Completable = Completable.fromAction {
        followRequestMethods.rejectFollowRequest(accountId)
    }
}
