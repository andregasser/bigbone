package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Account
import social.bigbone.api.method.DirectoryMethods
import social.bigbone.api.method.DirectoryMethods.AccountOrder

/**
 * Reactive implementation of [DirectoryMethods].
 * Allows access to API methods with endpoints having an "api/vX/directory" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/directory/">Mastodon directory API methods</a>
 */
class RxDirectoryMethods(client: MastodonClient) {

    private val directoryMethods = DirectoryMethods(client)

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
    ): Single<List<Account>> = Single.fromCallable {
        directoryMethods.listAccounts(local, order, offset, limit).execute()
    }
}
