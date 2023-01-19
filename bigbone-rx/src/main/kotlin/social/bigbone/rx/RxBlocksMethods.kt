package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.method.BlocksMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxBlocksMethods(client: MastodonClient) {
    private val blocksMethods = BlocksMethods(client)

    fun getBlocks(range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val blocks = blocksMethods.getBlocks(range)
                it.onSuccess(blocks.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
