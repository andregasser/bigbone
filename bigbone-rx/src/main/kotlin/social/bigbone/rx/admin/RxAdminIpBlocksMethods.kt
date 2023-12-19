package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminIpBlock
import social.bigbone.api.method.admin.AdminIpBlockMethods

/**
 * Reactive implementation of [AdminIpBlockMethods].
 *
 * Allow management of ip addresses blocked and to be blocked
 * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/">Mastodon admin/ip_blocks API methods</a>
 */
class RxAdminIpBlockMethods(client: MastodonClient) {

    private val adminIpBlockMethods = AdminIpBlockMethods(client)

    /**
     * Show information about all blocked IP ranges.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/ip_blocks/#get">Mastodon API documentation: methods/ip_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllIpBlocks(range: Range = Range()): Single<Pageable<AdminIpBlock>> = Single.fromCallable {
        adminIpBlockMethods.getAllIpBlocks(range = range).execute()
    }

    /**
     * Show information about a single IP block.
     * @param id The ID of the IpBlock in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/ip_blocks/#get">Mastodon API documentation: methods/ip_blocks/#get</a>
     */
    fun getBlockedIpRange(id: String): Single<AdminIpBlock> = Single.fromCallable {
        adminIpBlockMethods.getBlockedIpRange(id = id).execute()
    }


    /**
     * Add an IP address range to the list of IP blocks.
     * @param ipAddress The IP address and prefix to block.
     * @param severity The policy to apply to this IP range.
     * @param comment The reason for this IP block.
     * @param expiresInSeconds The number of seconds in which this IP block will expire.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#create">Mastodon API documentation: admin/ip_blocks/#create</a>
     */
    fun blockIpRange(ipAddress: String, severity: AdminIpBlock.Severity, comment: String?, expiresInSeconds: Int?): Single<AdminIpBlock> = Single.fromCallable {
        adminIpBlockMethods.blockIpRange(
            ipAddress = ipAddress,
            severity = severity,
            comment = comment,
            expiresInSeconds = expiresInSeconds
        ).execute()
    }


    /**
     * Change parameters for an existing IP block.
     * @param id The ID of the IpBlock in the database.
     * @param ipAddress The IP address and prefix to block.
     * @param severity The policy to apply to this IP range.
     * @param comment The reason for this IP block.
     * @param expiresInSeconds The number of seconds in which this IP block will expire.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#update">Mastodon API documentation: admin/ip_blocks/#update</a>
     */
    fun updateBlockedIpRange(id: String, ipAddress: String, severity: AdminIpBlock.Severity, comment: String?, expiresInSeconds: Int?): Single<AdminIpBlock> = Single.fromCallable {
        adminIpBlockMethods.updateBlockedIpRange(
            id = id,
            ipAddress = ipAddress,
            severity = severity,
            comment = comment,
            expiresInSeconds = expiresInSeconds
        ).execute()
    }


    /**
     * Lift a block against an IP range.
     * @param id The ID of the IpBlock in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/#delete">Mastodon API documentation: admin/ip_blocks/#delete</a>
     */
    fun removeIpBlock(id: String): Completable = Completable.fromAction {
        adminIpBlockMethods.removeIpBlock(id = id)
    }

}
