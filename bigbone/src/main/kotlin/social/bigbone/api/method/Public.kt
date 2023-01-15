package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.entity.Instance
import social.bigbone.api.entity.Results

class Public(private val client: MastodonClient) {

    /**
     * Retrieve instance details.
     * @see <a href="https://docs.joinmastodon.org/entities/V1_Instance/">Mastodon API documentation: entities/V1_Instance</a>
     */
    fun getInstance(): MastodonRequest<Instance> {
        return client.getMastodonRequest(
            endpoint = "api/v1/instance",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Search for content in accounts, statuses and hashtags.
     * @param query string to search for
     * @param resolve whether to resolve non-local accounts
     * @see <a href="https://docs.joinmastodon.org/methods/search/">Mastodon API documentation: methods/search</a>
     */
    @JvmOverloads
    fun search(query: String, resolve: Boolean = false): MastodonRequest<Results> {
        return client.getMastodonRequest(
            endpoint = "api/v2/search",
            method = MastodonClient.Method.GET,
            parameters = Parameter().apply {
                append("q", query)
                if (resolve) {
                    append("resolve", true)
                }
            }
        )
    }
}
