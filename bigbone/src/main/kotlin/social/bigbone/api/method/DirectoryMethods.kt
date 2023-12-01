package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.Account

/**
 * Allows access to API methods with endpoints having an "api/vX/directory" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/directory/">Mastodon directory API methods</a>
 */
class DirectoryMethods(private val client: MastodonClient) {

    /**
     * When listing, use [AccountOrder.ACTIVE] to sort by most recently posted statuses or [AccountOrder.NEW] to sort
     * by most recently created profiles.
     */
    enum class AccountOrder {
        ACTIVE,
        NEW
    }

    /**
     * List accounts visible in the directory.
     * @param local If true, returns only local accounts.
     * @param order Use [AccountOrder.ACTIVE] to sort by most recently posted statuses (default)
     *  or [AccountOrder.NEW] to sort by most recently created profiles.
     * @param offset Skip the first n results (defaults to 0, meaning no skipping).
     * @param limit How many accounts to load. Defaults to 40 accounts. Max 80 accounts.
     * @see <a href="https://docs.joinmastodon.org/methods/directory/#get">Mastodon API documentation: methods/directory/#get</a>
     */
    @JvmOverloads
    fun listAccounts(
        local: Boolean,
        order: AccountOrder = AccountOrder.ACTIVE,
        offset: Int = 0,
        limit: Int? = null
    ): MastodonRequest<List<Account>> {
        return client.getMastodonRequestForList(
            endpoint = "api/v1/directory",
            method = MastodonClient.Method.GET,
            parameters = Parameters().apply {
                append("local", local)
                when (order) {
                    AccountOrder.ACTIVE -> append("order", "active")
                    AccountOrder.NEW -> append("order", "new")
                }
                append("offset", offset)
                limit?.let { append("limit", limit) }
            }
        )
    }
}
