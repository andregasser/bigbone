package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.Tag
import social.bigbone.api.entity.TrendsLink
import social.bigbone.api.method.TrendMethods

/**
 * Reactive implementation of [TrendMethods].
 *
 * View trending tags, statuses, or links, i.e. those that are currently being used more frequently than usual.
 * @see <a href="https://docs.joinmastodon.org/methods/trends/">Mastodon trends API methods</a>
 */
class RxTrendMethods(client: MastodonClient) {
    private val trendMethods = TrendMethods(client)

    /**
     * Tags that are being used more frequently within the past week.
     *
     * @param offset Skip the first n results.
     * @param limit Maximum number of results to return. Defaults to 10 tags. Max 20 tags.
     * @see <a href="https://docs.joinmastodon.org/methods/trends/#tags">Mastodon API documentation: methods/trends/#tags</a>
     */
    @JvmOverloads
    fun getTrendingTags(
        offset: Int? = null,
        limit: Int = 10
    ): Single<List<Tag>> = Single.fromCallable { trendMethods.getTrendingTags(offset, limit).execute() }

    /**
     * Statuses that have been interacted with more than others.
     *
     * @param offset Skip the first n results.
     * @param limit Maximum number of results to return. Defaults to 20 statuses. Max 40 statuses.
     * @see <a href="https://docs.joinmastodon.org/methods/trends/#statuses">Mastodon API documentation: methods/trends/#statuses</a>
     */
    fun getTrendingStatuses(
        offset: Int? = null,
        limit: Int = 20
    ): Single<List<Status>> = Single.fromCallable { trendMethods.getTrendingStatuses(offset, limit).execute() }

    /**
     * Links that have been shared more than others.
     *
     * @param offset Skip the first n results.
     * @param limit Maximum number of results to return. Defaults to 10 links. Max 20 links.
     * @see <a href="https://docs.joinmastodon.org/methods/trends/#links">Mastodon API documentation: methods/trends/#links</a>
     */
    @JvmOverloads
    fun getTrendingLinks(
        offset: Int? = null,
        limit: Int = 10
    ): Single<List<TrendsLink>> = Single.fromCallable { trendMethods.getTrendingLinks(offset, limit).execute() }
}
