package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Relationship
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.Token
import social.bigbone.api.method.AccountMethods

/**
 * Reactive implementation of [AccountMethods].
 * Allows access to API methods with endpoints having an "api/vX/accounts" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/accounts/">Mastodon accounts API methods</a>
 */
class RxAccountMethods(client: MastodonClient) {

    private val accountMethods = AccountMethods(client)

    fun registerAccount(
        username: String,
        email: String,
        password: String,
        agreement: Boolean,
        locale: String,
        reason: String?
    ): Single<Token> = Single.fromCallable {
        accountMethods.registerAccount(username, email, password, agreement, locale, reason).execute()
    }

    fun getAccount(accountId: String): Single<Account> = Single.fromCallable {
        accountMethods.getAccount(accountId).execute()
    }

    fun verifyCredentials(): Single<Account> = Single.fromCallable {
        accountMethods.verifyCredentials().execute()
    }

    fun updateCredentials(displayName: String?, note: String?, avatar: String?, header: String?): Single<Account> =
        Single.fromCallable { accountMethods.updateCredentials(displayName, note, avatar, header).execute() }

    @JvmOverloads
    fun getFollowers(accountId: String, range: Range = Range()): Single<Pageable<Account>> = Single.fromCallable {
        accountMethods.getFollowers(accountId, range).execute()
    }

    @JvmOverloads
    fun getFollowing(accountId: String, range: Range = Range()): Single<Pageable<Account>> = Single.fromCallable {
        accountMethods.getFollowing(accountId, range).execute()
    }

    @JvmOverloads
    fun getStatuses(
        accountId: String,
        onlyMedia: Boolean = false,
        excludeReplies: Boolean = false,
        pinned: Boolean = false,
        range: Range = Range()
    ): Single<Pageable<Status>> = Single.fromCallable {
        accountMethods.getStatuses(accountId, onlyMedia, excludeReplies, pinned, range).execute()
    }

    fun followAccount(accountId: String): Single<Relationship> = Single.fromCallable {
        accountMethods.followAccount(accountId).execute()
    }

    fun unfollowAccount(accountId: String): Single<Relationship> = Single.fromCallable {
        accountMethods.unfollowAccount(accountId).execute()
    }

    fun blockAccount(accountId: String): Single<Relationship> = Single.fromCallable {
        accountMethods.blockAccount(accountId).execute()
    }

    fun unblockAccount(accountId: String): Single<Relationship> = Single.fromCallable {
        accountMethods.unblockAccount(accountId).execute()
    }

    fun muteAccount(accountId: String): Single<Relationship> = Single.fromCallable {
        accountMethods.muteAccount(accountId).execute()
    }

    fun unmuteAccount(accountId: String): Single<Relationship> = Single.fromCallable {
        accountMethods.unmuteAccount(accountId).execute()
    }

    fun getRelationships(accountIds: List<String>): Single<List<Relationship>> = Single.fromCallable {
        accountMethods.getRelationships(accountIds).execute()
    }

    @JvmOverloads
    fun searchAccounts(query: String, limit: Int? = null): Single<List<Account>> = Single.fromCallable {
        accountMethods.searchAccounts(query, limit).execute()
    }
}
