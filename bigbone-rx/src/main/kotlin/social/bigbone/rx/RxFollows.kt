package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Account
import social.bigbone.api.method.Follows
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxFollows(client: MastodonClient) {
    val follows = Follows(client)

    fun postRemoteFollow(uri: String): Single<Account> {
        return Single.create {
            try {
                val account = follows.postRemoteFollow(uri)
                it.onSuccess(account.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
