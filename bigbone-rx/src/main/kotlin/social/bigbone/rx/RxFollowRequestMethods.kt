package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.method.FollowRequestMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

/**
 * Reactive implementation of [FollowRequestMethods].
 * Allows access to API methods with endpoints having an "api/vX/follow_requests" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/follow_requests/">Mastodon follow_requests API methods</a>
 */
class RxFollowRequestMethods(client: MastodonClient) {
    private val followRequestMethods = FollowRequestMethods(client)

    fun getFollowRequests(range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = followRequestMethods.getFollowRequests(range)
                it.onSuccess(accounts.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun authorizeFollowRequest(accountId: String): Completable {
        return Completable.create {
            try {
                followRequestMethods.authorizeFollowRequest(accountId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun rejectFollowRequest(accountId: String): Completable {
        return Completable.create {
            try {
                followRequestMethods.rejectFollowRequest(accountId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
