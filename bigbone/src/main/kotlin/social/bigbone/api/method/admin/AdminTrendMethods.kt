package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.TrendsLink
import social.bigbone.api.entity.admin.AdminTag

/**
 * Obtain data about trending links, statuses, and tags on the administered server.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/trends/">Mastodon admin/trends API methods</a>
 */
class AdminTrendMethods(private val client: MastodonClient) {

    private val adminTrendsEndpoint = "api/v1/admin/trends"

    /**
     * Links that have been shared more than others, including unapproved and unreviewed links.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/trends/#links">Mastodon API documentation: admin/trends/#links</a>
     */
    fun getTrendingLinks(): MastodonRequest<List<TrendsLink>> {
        return client.getMastodonRequestForList(
            endpoint = "$adminTrendsEndpoint/links",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Statuses that have been interacted with more than others, including unapproved and unreviewed statuses.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/trends/#statuses">Mastodon API documentation: admin/trends/#statuses</a>
     */
    fun getTrendingStatuses(): MastodonRequest<List<Status>> {
        return client.getMastodonRequestForList(
            endpoint = "$adminTrendsEndpoint/statuses",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Tags that are being used more frequently within the past week, including unapproved and unreviewed tags.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/trends/#tags">Mastodon API documentation: admin/trends/#tags</a>
     */
    fun getTrendingTags(): MastodonRequest<List<AdminTag>> {
        return client.getMastodonRequestForList(
            endpoint = "$adminTrendsEndpoint/tags",
            method = MastodonClient.Method.GET
        )
    }
}
