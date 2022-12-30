package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.method.FollowRequests
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxFollowRequests(client: MastodonClient) {
    val followRequests = FollowRequests(client)

    fun getFollowRequests(range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = followRequests.getFollowRequests(range)
                it.onSuccess(accounts.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postAuthorize(accountId: String): Completable {
        return Completable.create {
            try {
                followRequests.postAuthorize(accountId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postReject(accountId: String): Completable {
        return Completable.create {
            try {
                followRequests.postReject(accountId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
