package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.MastodonList

class ListsMethods(private val client: MastodonClient) {

    /**
     * Gets all lists for the current user.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#get">Mastodon API documentation: methods/lists/#get</a>
     */
    fun getLists(): MastodonRequest<List<MastodonList>> {
        return client.getMastodonRequestForList(
            endpoint = "api/v1/lists",
            method = MastodonClient.Method.GET
        )
    }
}
