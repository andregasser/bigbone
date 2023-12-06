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

    /**
     * Search for content in accounts, statuses and hashtags.
     * @see <a href="https://github.com/mastodon/documentation/issues/1336">Mastodon issue: incomplete documentation of offset/type parameters</a>
     * @param query string to search for
     * @param type to specify if you are looking for a specific typology
     * @param resolve whether to resolve non-local accounts
     * @param following whether to include accounts that the user is following
     * @param excludeUnreviewed whether to look for trending tags
     * @param accountId to only return statuses authored by the user account
     * @param maxId to return results whose id will be lesser than this one
     * @param minId to return results whose id will be newer than this one
     * @param limit to limit number of results within a range of 20-40 per category
     * @param offset to skip the first n results; only used if a type is specified
     * @see <a href="https://docs.joinmastodon.org/methods/search/">Mastodon API documentation: methods/search</a>
     */
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
        return Single.fromCallable {
            searchMethodsMethod.searchContent(
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
            ).execute()
        }
    }
}
