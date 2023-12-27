package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range

/**
 * Manage a user's blocked domains.
 * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/">Mastodon domain_blocks API methods</a>
 */
class DomainBlockMethods(private val client: MastodonClient) {

    private val domainBlocksEndpoint = "api/v1/domain_blocks"

    /**
     * View domains the user has blocked.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#get">Mastodon API documentation: methods/domain_blocks/#get</a>
     * @return [Pageable] of [String] representing the domains the user has blocked
     */
    @JvmOverloads
    fun getDomainBlocks(
        range: Range = Range()
    ): MastodonRequest<Pageable<String>> {
        return client.getPageableMastodonRequest<String>(
            endpoint = domainBlocksEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Block a domain to hide all public posts from it, hide all notifications from it,
     * remove all followers from it, prevent following new users from it (but does not remove existing follows).
     *
     * @param domain Domain to block
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#block">Mastodon API documentation: methods/domain_blocks/#block</a>
     * @throws IllegalArgumentException if [domain] is blank or contains spaces
     */
    @Throws(IllegalArgumentException::class)
    fun blockDomain(
        domain: String
    ) {
        require(domain.isNotBlank()) { "domain must not be blank" }
        require(!domain.contains(' ')) { "domain must not contain spaces" }

        return client.performAction(
            endpoint = domainBlocksEndpoint,
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Remove a domain block, if it exists in the user’s array of blocked domains.
     *
     * @param domain Domain to unblock
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#unblock">Mastodon API documentation: methods/domain_blocks/#unblock</a>
     * @throws IllegalArgumentException if [domain] is blank or contains spaces
     */
    @Throws(IllegalArgumentException::class)
    fun unblockDomain(
        domain: String
    ) {
        require(domain.isNotBlank()) { "domain must not be blank" }
        require(!domain.contains(' ')) { "domain must not contain spaces" }

        return client.performAction(
            endpoint = domainBlocksEndpoint,
            method = MastodonClient.Method.DELETE
        )
    }
}
