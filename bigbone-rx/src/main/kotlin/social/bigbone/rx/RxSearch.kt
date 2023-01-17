package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Results
import social.bigbone.api.method.Search

class RxSearch(client: MastodonClient) {
    private val searchMethod = Search(client)

    fun searchContent(query: String, resolve: Boolean = true): Single<Results> {
        return Single.create {
            try {
                val results = searchMethod.searchContent(query, resolve)
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
