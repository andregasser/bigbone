package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminIpBlock

/**
 * Allow management of ip addresses blocked and to be blocked.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/">Mastodon admin/ip_blocks API methods</a>
 */
class AdminIpBlockMethods(private val client: MastodonClient) {

    private val adminIpBlockEndpoint = "api/v1/admin/ip_blocks"

    /**
     * Show information about all blocked IP ranges.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#get">Mastodon API documentation: admin/ip_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllIpBlocks(range: Range = Range()): MastodonRequest<Pageable<AdminIpBlock>> {
        return client.getPageableMastodonRequest(
            endpoint = adminIpBlockEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Show information about a single IP block.
     * @param id The ID of the IpBlock in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#get-one">Mastodon API documentation: admin/ip_blocks/#get-one</a>
     */
    fun getBlockedIpRange(id: String): MastodonRequest<AdminIpBlock> {
        return client.getMastodonRequest(
            endpoint = "$adminIpBlockEndpoint/$id",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Add an IP address range to the list of IP blocked ones.
     * @param ipAddress The IP address and prefix to block. Defaults to 0.0.0.0/32.
     * @param severity The policy to apply to this IP range.
     * @param comment The reason for this IP block.
     * @param expiresInSeconds The number of seconds in which this IP block will expire.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#create">Mastodon API documentation: admin/ip_blocks/#create</a>
     */
    @JvmOverloads
    fun blockIpRange(
        ipAddress: String,
        severity: AdminIpBlock.Severity,
        comment: String? = null,
        expiresInSeconds: Int? = null
    ): MastodonRequest<AdminIpBlock> {
        return client.getMastodonRequest(
            endpoint = adminIpBlockEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("ip", ipAddress)
                append("severity", severity.apiName)
                comment?.let { append("comment", it) }
                expiresInSeconds?.let { append("expires_in", it) }
            }
        )
    }

    /**
     * Change parameters for an existing IP block.
     * @param id The ID of the IpBlock in the database.
     * @param ipAddress The IP address and prefix to block.
     * @param severity The policy to apply to this IP range.
     * @param comment The reason for this IP block.
     * @param expiresInSeconds The number of seconds in which this IpBlock will expire.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#update">Mastodon API documentation: admin/ip_blocks/#update</a>
     */
    @JvmOverloads
    fun updateBlockedIpRange(
        id: String,
        ipAddress: String,
        severity: AdminIpBlock.Severity,
        comment: String? = null,
        expiresInSeconds: Int? = null
    ): MastodonRequest<AdminIpBlock> {
        return client.getMastodonRequest(
            endpoint = "$adminIpBlockEndpoint/$id",
            method = MastodonClient.Method.PUT,
            parameters = Parameters().apply {
                append("ip", ipAddress)
                append("severity", severity.apiName)
                comment?.let { append("comment", it) }
                expiresInSeconds?.let { append("expires_in", it) }
            }
        )
    }

    /**
     * Lift a block against an IP range.
     * @param id The ID of the IpBlock in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#delete">Mastodon API documentation: admin/ip_blocks/#delete</a>
     */
    fun removeIpBlock(id: String) {
        client.performAction(
            endpoint = "$adminIpBlockEndpoint/$id",
            method = MastodonClient.Method.DELETE
        )
    }
}
