package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminCanonicalEmailBlock
import social.bigbone.api.entity.admin.BlockCanonicalEmailVariant

/**
 * Block certain email addresses by their hash.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/">Mastodon admin/canonical_email_blocks API methods</a>
 */
class AdminCanonicalEmailBlockMethods(private val client: MastodonClient) {

    private val adminCanonicalEmailBlockEndpoint = "api/v1/admin/canonical_email_blocks"

    /**
     * List all canonical email blocks.
     *
     * @param range optional Range for the pageable return value
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#get">
     *     Mastodon API documentation: admin/canonical_email_blocks/#get</a>
     */
    @JvmOverloads
    fun getAllCanonicalEmailBlocks(range: Range = Range()): MastodonRequest<Pageable<AdminCanonicalEmailBlock>> {
        return client.getPageableMastodonRequest(
            endpoint = adminCanonicalEmailBlockEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters()
        )
    }

    /**
     * Show a single canonical email block.
     *
     * @param id The ID of the Admin::CanonicalEmailBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#get-one">
     *     Mastodon API documentation: admin/canonical_email_blocks/#get-one</a>
     */
    fun getCanonicalEmailBlock(id: String): MastodonRequest<AdminCanonicalEmailBlock> {
        return client.getMastodonRequest(
            endpoint = "$adminCanonicalEmailBlockEndpoint/$id",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Block a canonical email.
     *
     * @param variant One of [BlockCanonicalEmailVariant]s to define how to block a canonical email.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#create">
     *     Mastodon API documentation: admin/canonical_email_blocks/#create</a>
     */
    fun blockCanonicalEmail(variant: BlockCanonicalEmailVariant): MastodonRequest<AdminCanonicalEmailBlock> {
        return client.getMastodonRequest(
            endpoint = adminCanonicalEmailBlockEndpoint,
            method = MastodonClient.Method.POST,
            parameters = variant.appendToParameters()
        )
    }

    /**
     * Delete a canonical email block.
     *
     * @param id The ID of the Admin::CanonicalEmailBlock in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/#delete">
     *     Mastodon API documentation: admin/canonical_email_blocks/#delete</a>
     */
    fun removeCanonicalEmailBlock(id: String) {
        client.performAction(
            endpoint = "$adminCanonicalEmailBlockEndpoint/$id",
            method = MastodonClient.Method.DELETE
        )
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
    fun canonicalizeAndHashEmailAddress(emailAddress: String): MastodonRequest<List<AdminCanonicalEmailBlock>> {
        return client.getMastodonRequestForList(
            endpoint = "$adminCanonicalEmailBlockEndpoint/test",
            method = MastodonClient.Method.POST,
            parameters = Parameters().append("email", emailAddress)
        )
    }
}
