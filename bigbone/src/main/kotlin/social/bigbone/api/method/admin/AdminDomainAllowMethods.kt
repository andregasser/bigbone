package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminDomainAllow

/**
 * Allow certain domains to federate.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_allows/">Mastodon admin/domain_allows API methods</a>
 */
class AdminDomainAllowMethods(private val client: MastodonClient) {

    private val adminDomainAllowEndpoint = "api/v1/admin/domain_allows"

    /**
     * Show information about all allowed domains.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/domain_allows/#get">Mastodon API documentation: methods/domain_allows/#get</a>
     */
    @JvmOverloads
    fun getAllAllowedDomains(range: Range = Range()): MastodonRequest<Pageable<AdminDomainAllow>> {
        return client.getPageableMastodonRequest(
            endpoint = adminDomainAllowEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Show information about a single allowed domain.
     * @param id The ID of the DomainAllow in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/domain_allows/#get-one">Mastodon API documentation: methods/domain_allows/#get-one</a>
     */
    fun getAllowedDomain(id: String): MastodonRequest<AdminDomainAllow> {
        return client.getMastodonRequest(
            endpoint = "$adminDomainAllowEndpoint/$id",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Add a domain to the list of domains allowed to federate, to be used when the instance is in allow-list federation mode.
     * @param domain The domain to allow federation with.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_allows/#create">Mastodon API documentation: admin/domain_allows/#create</a>
     */
    fun allowDomainToFederate(domain: String): MastodonRequest<AdminDomainAllow> {
        return client.getMastodonRequest(
            endpoint = adminDomainAllowEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("domain", domain)
            }
        )
    }

    /**
     * Delete a domain from the allowed domains list.
     * @param id The ID of the DomainAllow in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_allows/#delete">Mastodon API documentation: admin/domain_allows/#delete</a>
     */
    fun removeAllowedDomain(id: String) {
        client.performAction(
            endpoint = "$adminDomainAllowEndpoint/$id",
            method = MastodonClient.Method.DELETE
        )
    }
}
