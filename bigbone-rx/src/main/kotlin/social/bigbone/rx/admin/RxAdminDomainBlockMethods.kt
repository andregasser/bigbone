package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminDomainBlock
import social.bigbone.api.entity.admin.AdminDomainBlock.Severity
import social.bigbone.api.method.admin.AdminDomainBlockMethods

/**
 * Reactive implementation of [AdminDomainBlockMethods].
 *
 * Disallow certain domains to federate.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_blocks/">Mastodon admin/domain_blocks API methods</a>
 */
class RxAdminDomainBlockMethods(client: MastodonClient) {

    private val adminDomainBlockMethods = AdminDomainBlockMethods(client)

    /**
     * Show information about all blocked domains.

     * @param range optional Range for the pageable return value
     *
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#get">Mastodon API documentation: methods/domain_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllBlockedDomains(range: Range = Range()): Single<Pageable<AdminDomainBlock>> = Single.fromCallable {
        adminDomainBlockMethods.getAllBlockedDomains(range = range).execute()
    }

    /**
     * Show information about a single blocked domain.
     *
     * @param id The ID of the DomainBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/domain_blocks/#get-one">Mastodon API documentation: methods/domain_blocks/#get-one</a>
     */
    fun getBlockedDomain(id: String): Single<AdminDomainBlock> = Single.fromCallable {
        adminDomainBlockMethods.getBlockedDomain(id = id).execute()
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
    ): Single<AdminDomainBlock> = Single.fromCallable {
        adminDomainBlockMethods.blockDomain(
            domain = domain,
            severity = severity,
            rejectMedia = rejectMedia,
            rejectReports = rejectReports,
            obfuscate = obfuscate,
            privateComment = privateComment,
            publicComment = publicComment
        ).execute()
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
    ): Single<AdminDomainBlock> = Single.fromCallable {
        adminDomainBlockMethods.updateBlockedDomain(
            id = id,
            severity = severity,
            rejectMedia = rejectMedia,
            rejectReports = rejectReports,
            obfuscate = obfuscate,
            privateComment = privateComment,
            publicComment = publicComment
        ).execute()
    }

    /**
     * Lift a block against a domain.
     *
     * @param id The ID of the DomainBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/domain_blocks/#delete">Mastodon API documentation: admin/domain_blocks/#delete</a>
     */
    fun removeBlockedDomain(id: String): Completable = Completable.fromAction {
        adminDomainBlockMethods.removeBlockedDomain(id = id)
    }
}
