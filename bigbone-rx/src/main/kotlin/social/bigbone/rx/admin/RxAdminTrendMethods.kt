package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.TrendsLink
import social.bigbone.api.entity.admin.AdminTag
import social.bigbone.api.method.admin.AdminTrendMethods

/**
 * Reactive implementation of [AdminTrendMethods].
 *
 * Obtain data about trending links, statuses, and tags on the administered server.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/trends/">Mastodon admin/trends API methods</a>
 */
class RxAdminTrendMethods(client: MastodonClient) {

    private val adminTrendMethods = AdminTrendMethods(client)

    /**
     * Links that have been shared more than others, including unapproved and unreviewed links.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/trends/#links">Mastodon API documentation: admin/trends/#links</a>
     */
    fun getTrendingLinks(): Single<List<TrendsLink>> = Single.fromCallable {
        adminTrendMethods.getTrendingLinks().execute()
    }

    /**
     * Statuses that have been interacted with more than others, including unapproved and unreviewed statuses.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/trends/#statuses">Mastodon API documentation: admin/trends/#statuses</a>
     */
    fun getTrendingStatuses(): Single<List<Status>> = Single.fromCallable {
        adminTrendMethods.getTrendingStatuses().execute()
    }

    /**
     * Tags that are being used more frequently within the past week, including unapproved and unreviewed tags.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/trends/#tags">Mastodon API documentation: admin/trends/#tags</a>
     */
    fun getTrendingTags(): Single<List<AdminTag>> = Single.fromCallable {
        adminTrendMethods.getTrendingTags().execute()
    }
}
