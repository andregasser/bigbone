package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.Suggestion

/**
 * Allows access to API methods with endpoints having an "api/vX/suggestions" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/suggestions/">Mastodon API documentation: methods/suggestions/</a>
 */
class SuggestionMethods(private val client: MastodonClient) {

    private val suggestionsEndpointV2 = "/api/v2/suggestions"
    private val suggestionsEndpointV1 = "/api/v1/suggestions"

    /**
     * Accounts that are promoted by staff, or that the user has had past positive interactions with, but is not yet following.
     * @param limit to limit number of results
     * @see <a href="https://docs.joinmastodon.org/methods/suggestions/#v2">Mastodon API documentation: methods/suggestions/#v2</a>
     */
    fun getSuggestions(limit: Int? = null): MastodonRequest<List<Suggestion>> {
        if (limit != null && (limit <= 0 || limit > QUERY_RESULT_LIMIT)) {
            throw IllegalArgumentException("Limit param must be greater than zero and not be higher than $QUERY_RESULT_LIMIT but was $limit")
        }
        return client.getMastodonRequestForList(
            endpoint = suggestionsEndpointV2,
            method = MastodonClient.Method.GET,
            parameters = Parameters().apply {
                append("limit", limit ?: 40)
            }
        )
    }

    /**
     * Remove an account from follow suggestions.
     * @param accountId of the account of interest
     * @see <a href="https://docs.joinmastodon.org/methods/suggestions/#v1">Mastodon API documentation: methods/suggestions/#v1</a>
     */
    fun removeSuggestion(accountId: String) {
        client.performAction(
            endpoint = "$suggestionsEndpointV1/$accountId",
            method = MastodonClient.Method.DELETE
        )
    }

    companion object {
        private const val QUERY_RESULT_LIMIT = 80
    }
}
