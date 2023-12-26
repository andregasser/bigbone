package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminCanonicalEmailBlock
import social.bigbone.api.entity.admin.BlockCanonicalEmailVariant
import social.bigbone.api.method.admin.AdminCanonicalEmailBlockMethods

/**
 * Reactive implementation of [AdminCanonicalEmailBlockMethods].
 *
 * Block certain email addresses by their hash.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/">Mastodon admin/canonical_email_blocks API methods</a>
 */
class RxAdminEmailDomainBlockMethods(client: MastodonClient) {

    private val adminCanonicalEmailBlockMethods = AdminCanonicalEmailBlockMethods(client)

    /**
     * List all canonical email blocks.
     *
     * @param range optional Range for the pageable return value
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#get">
     *     Mastodon API documentation: admin/canonical_email_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllCanonicalEmailBlocks(range: Range = Range()): Single<Pageable<AdminCanonicalEmailBlock>> =
        Single.fromCallable { adminCanonicalEmailBlockMethods.getAllCanonicalEmailBlocks(range).execute() }

    /**
     * Show a single canonical email block.
     *
     * @param id The ID of the Admin::CanonicalEmailBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#get-one">
     *     Mastodon API documentation: admin/canonical_email_blocks/#get-one</a>
     */
    fun getEmailDomainBlock(id: String): Single<AdminCanonicalEmailBlock> = Single.fromCallable {
        adminCanonicalEmailBlockMethods.getCanonicalEmailBlock(id).execute()
    }

    /**
     * Block a canonical email.
     *
     * @param variant One of [BlockCanonicalEmailVariant]s to define how to block a canonical email.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#create">
     *     Mastodon API documentation: admin/canonical_email_blocks/#create</a>
     */
    fun blockEmailDomain(variant: BlockCanonicalEmailVariant): Single<AdminCanonicalEmailBlock> =
        Single.fromCallable { adminCanonicalEmailBlockMethods.blockCanonicalEmail(variant).execute() }

    /**
     * Delete a canonical email block.
     *
     * @param id The ID of the Admin::CanonicalEmailBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#delete">
     *     Mastodon API documentation: admin/canonical_email_blocks/#delete</a>
     */
    fun removeCanonicalEmailBlock(id: String) = Completable.fromAction {
        adminCanonicalEmailBlockMethods.removeCanonicalEmailBlock(id)
    }

    /**
     * Canonicalize and hash an email address.
     *
     * @param emailAddress The email to canonicalize and hash.
     * @return All matching canonical email blocks.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#test">
     *     Mastodon API documentation admin/canonical_email_blocks/#test</a>
     */
    fun canonicalizeAndHashEmailAddress(emailAddress: String): Single<List<AdminCanonicalEmailBlock>> =
        Single.fromCallable { adminCanonicalEmailBlockMethods.canonicalizeAndHashEmailAddress(emailAddress).execute() }
}
