package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Account
import social.bigbone.api.method.DirectoryMethods

/**
 * Reactive implementation of [DirectoryMethods].
 * Allows access to API methods with endpoints having an "api/vX/directory" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/directory/">Mastodon directory API methods</a>
 */
class RxDirectoryMethods(client: MastodonClient) {
    private val directoryMethods = DirectoryMethods(client)

    @JvmOverloads
    fun listAccounts(
        local: Boolean,
        order: DirectoryMethods.AccountOrder = DirectoryMethods.AccountOrder.ACTIVE,
        offset: Int = 0,
        limit: Int? = null
    ): Single<List<Account>> =
        Single.fromCallable { directoryMethods.listAccounts(local, order, offset, limit).execute() }
}
