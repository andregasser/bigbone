package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.FavouritesMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxFavouritesMethods(client: MastodonClient) {
    val favouritesMethods = FavouritesMethods(client)

    fun getFavourites(range: Range = Range()): Single<Pageable<Status>> {
        return Single.create {
            try {
                val statuses = favouritesMethods.getFavourites(range)
                it.onSuccess(statuses.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
