package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminDomainBlock
import social.bigbone.api.entity.admin.AdminDomainBlock.Severity

/**
 * Disallow certain domains to federate.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_blocks/">Mastodon admin/domain_blocks API methods</a>
 */
class AdminDomainBlockMethods(private val client: MastodonClient) {

    private val adminDomainBlocksEndpoint = "api/v1/admin/domain_blocks"

    /**
     * Show information about all blocked domains.
     *
     * @param range optional Range for the pageable return value
     *
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#get">Mastodon API documentation: methods/domain_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllBlockedDomains(range: Range = Range()): MastodonRequest<Pageable<AdminDomainBlock>> {
        return client.getPageableMastodonRequest(
            endpoint = adminDomainBlocksEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Show information about a single blocked domain.
     *
     * @param id The ID of the DomainBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#get-one">Mastodon API documentation: methods/domain_blocks/#get-one</a>
     */
    fun getBlockedDomain(id: String): MastodonRequest<AdminDomainBlock> {
        return client.getMastodonRequest(
            endpoint = "$adminDomainBlocksEndpoint/$id",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Add a domain to the list of domains blocked from federating.
     *
     * @param domain The domain to block federation with.
     * @param severity Whether to apply a [Severity.SILENCE], [Severity.SUSPEND], or [Severity.NOOP] to the domain. Defaults to [Severity.SILENCE].
     * @param rejectMedia Whether media attachments should be rejected. Defaults to false.
     * @param rejectReports Whether reports from this domain should be rejected. Defaults to false.
     * @param obfuscate Whether to partially censor the domain when shown in public. Defaults to false.
     * @param privateComment A private note about this domain block, visible only to admins.
     * @param publicComment A public note about this domain block, optionally shown on the About page.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_blocks/#create">Mastodon API documentation: admin/domain_blocks/#create</a>
     */
    @JvmOverloads
    fun blockDomain(
        domain: String,
        severity: Severity? = null,
        rejectMedia: Boolean? = null,
        rejectReports: Boolean? = null,
        obfuscate: Boolean? = null,
        privateComment: String? = null,
        publicComment: String? = null
    ): MastodonRequest<AdminDomainBlock> {
        return client.getMastodonRequest(
            endpoint = adminDomainBlocksEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("domain", domain)

                severity?.let { append("severity", severity.apiName) }

                rejectMedia?.let { append("reject_media", rejectMedia) }
                rejectReports?.let { append("reject_reports", rejectReports) }

                obfuscate?.let { append("obfuscate", obfuscate) }

                privateComment?.let { append("private_comment", privateComment) }
                publicComment?.let { append("public_comment", publicComment) }
            }
        )
    }

    /**
     * Change parameters for an existing domain block.
     *
     * @param id The ID of the DomainBlock in the database.
     * @param severity Whether to apply a [Severity.SILENCE], [Severity.SUSPEND], or [Severity.NOOP] to the domain. Defaults to [Severity.SILENCE].
     * @param rejectMedia Whether media attachments should be rejected. Defaults to false.
     * @param rejectReports Whether reports from this domain should be rejected. Defaults to false.
     * @param obfuscate Whether to partially censor the domain when shown in public. Defaults to false.
     * @param privateComment A private note about this domain block, visible only to admins.
     * @param publicComment A public note about this domain block, optionally shown on the About page.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_blocks/#update">Mastodon API documentation: admin/domain_blocks/#update</a>
     */
    @JvmOverloads
    fun updateBlockedDomain(
        id: String,
        severity: Severity? = null,
        rejectMedia: Boolean? = null,
        rejectReports: Boolean? = null,
        obfuscate: Boolean? = null,
        privateComment: String? = null,
        publicComment: String? = null
    ): MastodonRequest<AdminDomainBlock> {
        return client.getMastodonRequest(
            endpoint = "$adminDomainBlocksEndpoint/$id",
            method = MastodonClient.Method.PUT,
            parameters = Parameters().apply {
                severity?.let { append("severity", severity.apiName) }

                rejectMedia?.let { append("reject_media", rejectMedia) }
                rejectReports?.let { append("reject_reports", rejectReports) }

                obfuscate?.let { append("obfuscate", obfuscate) }

                privateComment?.let { append("private_comment", privateComment) }
                publicComment?.let { append("public_comment", publicComment) }
            }
        )
    }

    /**
     * Lift a block against a domain.
     *
     * @param id The ID of the DomainBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_blocks/#delete">Mastodon API documentation: admin/domain_blocks/#delete</a>
     */
    fun removeBlockedDomain(id: String) {
        client.performAction(
            endpoint = "$adminDomainBlocksEndpoint/$id",
            method = MastodonClient.Method.DELETE
        )
    }
}
