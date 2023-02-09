package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.MastodonList
import social.bigbone.api.method.ListMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

/**
 * Reactive implementation of [ListMethods].
 * Allows access to API methods with endpoints having an "api/vX/lists" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/lists/">Mastodon lists API methods</a>
 */
class RxListMethods(client: MastodonClient) {
    private val listMethods = ListMethods(client)

    fun getLists(): Single<List<MastodonList>> {
        return Single.create {
            try {
                val lists = listMethods.getLists()
                it.onSuccess(lists.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
