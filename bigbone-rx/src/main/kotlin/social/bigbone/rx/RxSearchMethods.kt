package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Search
import social.bigbone.api.method.SearchMethods

class RxSearchMethods(client: MastodonClient) {
    private val searchMethodsMethod = SearchMethods(client)

    fun searchContent(query: String, resolve: Boolean = true): Single<Search> {
        return Single.create {
            try {
                val results = searchMethodsMethod.searchContent(query, resolve)
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
