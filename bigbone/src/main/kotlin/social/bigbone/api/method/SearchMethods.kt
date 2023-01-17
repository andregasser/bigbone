package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.entity.Results

class SearchMethods(private val client: MastodonClient) {

    /**
     * Search for content in accounts, statuses and hashtags.
     * @param query string to search for
     * @param resolve whether to resolve non-local accounts
     * @see <a href="https://docs.joinmastodon.org/methods/search/">Mastodon API documentation: methods/search</a>
     */
    @JvmOverloads
    fun searchContent(query: String, resolve: Boolean = false): MastodonRequest<Results> {
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
