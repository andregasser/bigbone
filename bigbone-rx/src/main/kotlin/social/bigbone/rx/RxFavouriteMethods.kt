package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.FavouriteMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxFavouriteMethods(client: MastodonClient) {
    val favouriteMethods = FavouriteMethods(client)

    fun getFavourites(range: Range = Range()): Single<Pageable<Status>> {
        return Single.create {
            try {
                val statuses = favouriteMethods.getFavourites(range)
                it.onSuccess(statuses.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
