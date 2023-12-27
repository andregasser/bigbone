package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminEmailDomainBlock

/**
 * Disallow certain email domains from signing up.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/">Mastodon admin/email_domain_blocks API methods</a>
 */
class AdminEmailDomainBlockMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/admin/email_domain_blocks"

    /**
     * Show information about all email domains blocked from signing up.
     *
     * @param range optional Range for the pageable return value
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/#get">Mastodon API documentation: admin/email_domain_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllEmailDomainBlocks(range: Range = Range()): MastodonRequest<Pageable<AdminEmailDomainBlock>> {
        return client.getPageableMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Show information about a single email domain that is blocked from signups.
     *
     * @param id The ID of the EmailDomainBlock in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/#get-one">
     *     Mastodon API documentation: admin/email_domain_blocks/#get-one</a>
     */
    fun getEmailDomainBlock(id: String): MastodonRequest<AdminEmailDomainBlock> {
        return client.getMastodonRequest(
            endpoint = "$endpoint/$id",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Add a domain to the list of email domains blocked from signups.
     *
     * @param domain The domain to block federation with.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/#create">Mastodon API documentation: admin/email_domain_blocks/#create</a>
     */
    fun blockEmailDomain(domain: String): MastodonRequest<AdminEmailDomainBlock> {
        return client.getMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().append("domain", domain)
        )
    }

    /**
     * Lift a block against an email domain.
     *
     * @param id The ID of the EmailDomainBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/#delete">Mastodon API documentation: admin/email_domain_blocks/#delete</a>
     */
    fun removeEmailDomainBlock(id: String) {
        client.performAction(
            endpoint = "$endpoint/$id",
            method = MastodonClient.Method.DELETE
        )
    }
}
