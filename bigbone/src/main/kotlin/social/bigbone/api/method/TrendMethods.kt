package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.Tag

/**
 * View hashtags that are currently being used more frequently than usual.
 * @see <a href="https://docs.joinmastodon.org/methods/trends/">Mastodon trends API methods</a>
 */
class TrendMethods(private val client: MastodonClient) {

    fun getTrendingTags(): MastodonRequest<List<Tag>> {
        return client.getMastodonRequest(
            endpoint = "api/v1/trends/tags",
            method = MastodonClient.Method.GET
        )
    }

    fun getTrendingStatuses(): MastodonRequest<List<Status>> {
        return client.getMastodonRequest(
            endpoint = "api/v1/trends/statuses",
            method = MastodonClient.Method.GET
        )
    }

    fun getTrendingLinks(): MastodonRequest<List<Status>> {
        return client.getMastodonRequest(
            endpoint = "api/v1/trends/statuses",
            method = MastodonClient.Method.GET
        )
    }
}