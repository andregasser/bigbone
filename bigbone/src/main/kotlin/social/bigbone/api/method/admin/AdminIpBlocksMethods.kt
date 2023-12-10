package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminIpBlock

/**
 * Allow management of ip addresses blocked and to be blocked
 * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/">Mastodon admin/ip_blocks API methods</a>
 */
class AdminIpBlocksMethods(private val client: MastodonClient) {

    private val adminIpBlocksEndpoint = "api/v1/admin/ip_blocks"
    private val ipSubdomainRegex = "^\\d+\\.\\d+\\.\\d+\\.\\d+/\\d+$".toRegex()

    /**
     * Show information about all blocked IP ranges.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/ip_blocks/#get">Mastodon API documentation: methods/ip_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllIpBlocks(range: Range = Range()): MastodonRequest<Pageable<AdminIpBlock>> {
        return client.getPageableMastodonRequest(
            endpoint = adminIpBlocksEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Show information about a single IP block.
     * @param id for the ID of the IpBlock in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/ip_blocks/#get">Mastodon API documentation: methods/ip_blocks/#get</a>
     */
    fun getSingleIpBlocked(id: String): MastodonRequest<AdminIpBlock> {
        return client.getMastodonRequest(
            endpoint = "$adminIpBlocksEndpoint/$id",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Add an IP address range to the list of IP blocks.
     * @param ipAddress The IP address and prefix to block.
     * @param severity The policy to apply to this IP range.
     * @param comment The reason for this IP block.
     * @param expiresIn The number of seconds in which this IP block will expire.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#create">Mastodon API documentation: admin/ip_blocks/#create</a>
     */
    fun addIpToTheBlockedOnes(ipAddress: String = "0.0.0.0/32", severity: AdminIpBlock.Severity, comment: String, expiresIn: Int): MastodonRequest<AdminIpBlock> {
        require(ipSubdomainRegex.matches(ipAddress)) { "The ip does not match the pattern to represent an ip address and subdomain" }
        return client.getMastodonRequest(
            endpoint = adminIpBlocksEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("ip", ipAddress)
                append("severity", severity.apiName)
                append("comment", comment)
                append("expires_in", expiresIn)
            }
        )
    }

    /**
     * Change parameters for an existing IP block.
     * @param id The ID of the DomainAllow in the database.
     * @param ipAddress The IP address and prefix to block.
     * @param severity The policy to apply to this IP range.
     * @param comment The reason for this IP block.
     * @param expiresIn The number of seconds in which this IP block will expire.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#update">Mastodon API documentation: admin/ip_blocks/#update</a>
     */
    fun updateSingleIpToTheBlockedOnes(id: String, ipAddress: String = "0.0.0.0/32", severity: AdminIpBlock.Severity, comment: String, expiresIn: Int): MastodonRequest<AdminIpBlock> {
        require(ipSubdomainRegex.matches(ipAddress)) { "The ip does not match the pattern to represent an ip address and subdomain" }
        return client.getMastodonRequest(
            endpoint = "$adminIpBlocksEndpoint/$id",
            method = MastodonClient.Method.PUT,
            parameters = Parameters().apply {
                append("ip", ipAddress)
                append("severity", severity.apiName)
                append("comment", comment)
                append("expires_in", expiresIn)
            }
        )
    }

    /**
     * Lift a block against an IP range.
     * @param id The ID of the DomainAllow in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#delete">Mastodon API documentation: admin/ip_blocks/#delete</a>
     */
    fun removeIpBlock(id: String) {
        client.performAction(
            endpoint = "$adminIpBlocksEndpoint/$id",
            method = MastodonClient.Method.DELETE
        )
    }
}
