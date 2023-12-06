package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.DomainBlock
import social.bigbone.api.entity.ExtendedDescription
import social.bigbone.api.entity.Instance
import social.bigbone.api.entity.InstanceActivity
import social.bigbone.api.entity.InstanceV1
import social.bigbone.api.entity.Rule
import social.bigbone.api.method.InstanceMethods

/**
 * Reactive implementation of [InstanceMethods].
 * Allows access to API methods with endpoints having an "api/vX/instance" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/instance/">Mastodon instance API methods</a>
 */
class RxInstanceMethods(client: MastodonClient) {
    private val instanceMethods = InstanceMethods(client)

    /**
     * Obtain general information about the server.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#v2">Mastodon API documentation: methods/instance/#v2</a>
     */
    fun getInstance(): Single<Instance> = Single.fromCallable { instanceMethods.getInstance().execute() }

    /**
     * Obtain general information about the server.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#v1">Mastodon API documentation: methods/instance/#v1</a>
     */
    @Deprecated(
        message = "Deprecated since 4.0.0. This method is just kept for backward compatibility.",
        replaceWith = ReplaceWith("getInstance()"),
        level = DeprecationLevel.WARNING
    )
    fun getInstanceV1(): Single<InstanceV1> = Single.fromCallable { instanceMethods.getInstanceV1().execute() }

    /**
     * Get the list of connected domains: Domains that this instance is aware of.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#peers">Mastodon API documentation: methods/instance/#peers</a>
     */
    fun getPeers(): Single<List<String>> = Single.fromCallable { instanceMethods.getPeers().execute() }

    /**
     * Instance activity over the last 3 months, binned weekly.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#activity">Mastodon API documentation: methods/instance/#activity</a>
     */
    fun getActivity(): Single<List<InstanceActivity>> = Single.fromCallable { instanceMethods.getActivity().execute() }

    /**
     * Rules that the users of this service should follow.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#rules">Mastodon API documentation: methods/instance/#rules</a>
     */
    fun getRules(): Single<List<Rule>> = Single.fromCallable { instanceMethods.getRules().execute() }

    /**
     * View moderated servers
     * Obtain a list of domains that have been blocked.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#domain_blocks">Mastodon API documentation: methods/instance/#domain_blocks</a>
     */
    fun getBlockedDomains(): Single<List<DomainBlock>> = Single.fromCallable { instanceMethods.getBlockedDomains().execute() }

    /**
     * Obtain an extended description of this server.
     * @see <a href="https://docs.joinmastodon.org/methods/instance/#extended_description">
     *     Mastodon API documentation: methods/instance/#extended_description</a>
     */
    fun getExtendedDescription(): Single<ExtendedDescription> = Single.fromCallable { instanceMethods.getExtendedDescription().execute() }
}
