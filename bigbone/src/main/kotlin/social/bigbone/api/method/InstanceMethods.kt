package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonMinServerVersion
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.DomainBlock
import social.bigbone.api.entity.ExtendedDescription
import social.bigbone.api.entity.Instance
import social.bigbone.api.entity.InstanceActivity
import social.bigbone.api.entity.InstanceV1
import social.bigbone.api.entity.Rule
import social.bigbone.api.exception.BigBoneVersionException
import social.bigbone.requireMinVersion

/**
 * Allows access to API methods with endpoints having an "api/vX/instance" prefix.
 * Discover information about a Mastodon website.
 * @see <a href="https://docs.joinmastodon.org/methods/instance/">Mastodon instance API methods</a>
 */
class InstanceMethods(private val client: MastodonClient) {

    private val endpointV1 = "api/v1/instance"
    private val endpointV2 = "api/v2/instance"

    /**
     * Obtain general information about the server.
     * @throws BigBoneVersionException if the Mastodon server version doesn't match the [MastodonMinServerVersion] for this method
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#v2">Mastodon API documentation: methods/instance/#v2</a>
     */
    @MastodonMinServerVersion("4.0.0")
    @Throws(BigBoneVersionException::class)
    fun getInstance(): MastodonRequest<Instance> {
        InstanceMethods::getInstance.requireMinVersion(client)

        return client.getMastodonRequest(
            endpoint = endpointV2,
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Obtain general information about the server.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#v1">Mastodon API documentation: methods/instance/#v1</a>
     */
    @Deprecated(
        message = "Deprecated since 4.0.0. This method is just kept for backward compatibility.",
        replaceWith = ReplaceWith("getInstance()"),
        level = DeprecationLevel.WARNING
    )
    fun getInstanceV1(): MastodonRequest<InstanceV1> {
        return client.getMastodonRequest(
            endpoint = endpointV1,
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Get the list of connected domains: Domains that this instance is aware of.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#peers">Mastodon API documentation: methods/instance/#peers</a>
     */
    fun getPeers(): MastodonRequest<List<String>> {
        return client.getMastodonRequestForList(
            endpoint = "$endpointV1/peers",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Instance activity over the last 3 months, binned weekly.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#activity">Mastodon API documentation: methods/instance/#activity</a>
     */
    fun getActivity(): MastodonRequest<List<InstanceActivity>> {
        return client.getMastodonRequestForList<InstanceActivity>(
            endpoint = "$endpointV1/activity",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Rules that the users of this service should follow.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#rules">Mastodon API documentation: methods/instance/#rules</a>
     */
    fun getRules(): MastodonRequest<List<Rule>> {
        return client.getMastodonRequestForList<Rule>(
            endpoint = "$endpointV1/rules",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * View moderated servers
     * Obtain a list of domains that have been blocked.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#domain_blocks">Mastodon API documentation: methods/instance/#domain_blocks</a>
     */
    fun getBlockedDomains(): MastodonRequest<List<DomainBlock>> {
        return client.getMastodonRequestForList<DomainBlock>(
            endpoint = "$endpointV1/domain_blocks",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Obtain an extended description of this server.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#extended_description">
     *     Mastodon API documentation: methods/instance/#extended_description</a>
     */
    fun getExtendedDescription(): MastodonRequest<ExtendedDescription> {
        return client.getMastodonRequest(
            endpoint = "$endpointV1/extended_description",
            method = MastodonClient.Method.GET
        )
    }
}
