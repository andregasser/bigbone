package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Search
import social.bigbone.api.method.SearchMethods
import social.bigbone.api.method.SearchMethods.SearchType

/**
 * Reactive implementation of [SearchMethods].
 * Allows access to API methods with endpoints having an "api/vX/search" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/search/">Mastodon search API methods</a>
 */
class RxSearchMethods(client: MastodonClient) {
    private val searchMethodsMethod = SearchMethods(client)

    @JvmOverloads
    fun searchContent(
        query: String,
        type: SearchType? = null,
        resolve: Boolean = false,
        following: Boolean = false,
        excludeUnreviewed: Boolean = false,
        accountId: String? = null,
        maxId: String? = null,
        minId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Single<Search> {
        return Single.create {
            try {
                val results = searchMethodsMethod.searchContent(
                    query = query,
                    type = type,
                    resolve = resolve,
                    following = following,
                    excludeUnreviewed = excludeUnreviewed,
                    accountId = accountId,
                    maxId = maxId,
                    minId = minId,
                    limit = limit,
                    offset = offset
                )
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
