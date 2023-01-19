package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.method.MuteMethods

class RxMuteMethods(client: MastodonClient) {
    private val muteMethods = MuteMethods(client)

    fun getMutes(range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = muteMethods.getMutes(range)
                it.onSuccess(accounts.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
