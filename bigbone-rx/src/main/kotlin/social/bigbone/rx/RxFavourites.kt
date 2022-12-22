package social.bigbone.rx

import io.reactivex.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Favourites
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxFavourites(client: MastodonClient) {
    val favourites = Favourites(client)

    fun getFavourites(range: Range = Range()): Single<Pageable<Status>> {
        return Single.create {
            try {
                val statuses = favourites.getFavourites(range)
                it.onSuccess(statuses.execute())
            } catch(throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
