package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminEmailDomainBlock
import social.bigbone.api.method.admin.AdminEmailDomainBlockMethods

/**
 * Reactive implementation of [AdminEmailDomainBlockMethods].
 *
 * Disallow certain email domains from signing up.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/">Mastodon admin/email_domain_blocks API methods</a>
 */
class RxAdminEmailDomainBlockMethods(client: MastodonClient) {

    private val adminEmailDomainBlockMethods = AdminEmailDomainBlockMethods(client)

    /**
     * Show information about all email domains blocked from signing up.
     *
     * @param range optional Range for the pageable return value
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/#get">Mastodon API documentation: admin/email_domain_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllEmailDomainBlocks(range: Range = Range()): Single<Pageable<AdminEmailDomainBlock>> = Single.fromCallable {
        adminEmailDomainBlockMethods.getAllEmailDomainBlocks(range).execute()
    }

    /**
     * Show information about a single email domain that is blocked from signups.
     *
     * @param id The ID of the EmailDomainBlock in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/#get-one">
     *     Mastodon API documentation: admin/email_domain_blocks/#get-one</a>
     */
    fun getEmailDomainBlock(id: String): Single<AdminEmailDomainBlock> = Single.fromCallable {
        adminEmailDomainBlockMethods.getEmailDomainBlock(id).execute()
    }

    /**
     * Add a domain to the list of email domains blocked from signups.
     *
     * @param domain The domain to block federation with.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/#create">Mastodon API documentation: admin/email_domain_blocks/#create</a>
     */
    fun blockEmailDomain(domain: String): Single<AdminEmailDomainBlock> = Single.fromCallable {
        adminEmailDomainBlockMethods.blockEmailDomain(domain).execute()
    }

    /**
     * Lift a block against an email domain.
     *
     * @param id The ID of the EmailDomainBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/#delete">Mastodon API documentation: admin/email_domain_blocks/#delete</a>
     */
    fun removeEmailDomainBlock(id: String) = Completable.fromAction {
        adminEmailDomainBlockMethods.removeEmailDomainBlock(id)
    }
}
