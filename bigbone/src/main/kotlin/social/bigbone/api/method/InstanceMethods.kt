package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.Instance

/**
 * Allows access to API methods with endpoints having an "api/vX/instance" prefix.
 * Discover information about a Mastodon website.
 * @see <a href="https://docs.joinmastodon.org/methods/instance/">Mastodon instance API methods</a>
 */
class InstanceMethods(private val client: MastodonClient) {

    private val instanceEndpointV1 = "/api/v1/instance"
    private val instanceEndpointV2 = "/api/v2/instance"

    /**
     * Obtain general information about the server.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#v2">Mastodon API documentation: methods/instance/#v2</a>
     */
    fun getInstance(): MastodonRequest<Instance> {
        return client.getMastodonRequest(
            endpoint = instanceEndpointV2,
            method = MastodonClient.Method.GET
        )
    }
}
