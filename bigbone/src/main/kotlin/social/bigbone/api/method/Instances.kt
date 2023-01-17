package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.Instance

class Instances(private val client: MastodonClient) {
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
}
