package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.method.Mutes

class RxMutes(client: MastodonClient) {
    val mutes = Mutes(client)

    fun getMutes(range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = mutes.getMutes(range)
                it.onSuccess(accounts.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
