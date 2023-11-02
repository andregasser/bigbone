package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.MastodonList
import social.bigbone.api.method.ListMethods

/**
 * Reactive implementation of [ListMethods].
 * Allows access to API methods with endpoints having an "api/vX/lists" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/lists/">Mastodon lists API methods</a>
 */
class RxListMethods(client: MastodonClient) {

    private val listMethods = ListMethods(client)

    fun getLists(): Single<List<MastodonList>> = Single.fromCallable { listMethods.getLists().execute() }

    fun getList(listId: String): Single<MastodonList> = Single.fromCallable { listMethods.getList(listId).execute() }

    @JvmOverloads
    fun createList(
        title: String,
        repliesPolicy: MastodonList.RepliesPolicy = MastodonList.RepliesPolicy.LIST
    ): Single<MastodonList> = Single.fromCallable { listMethods.createList(title, repliesPolicy).execute() }

    fun updateList(
        listId: String,
        title: String,
        repliesPolicy: MastodonList.RepliesPolicy
    ): Single<MastodonList> = Single.fromCallable { listMethods.updateList(listId, title, repliesPolicy).execute() }

    fun deleteList(listId: String): Completable = Completable.fromAction { listMethods.deleteList(listId) }

    @JvmOverloads
    fun getAccountsInList(listId: String, range: Range = Range()): Single<Pageable<Account>> = Single.fromCallable {
        listMethods.getAccountsInList(listId, range).execute()
    }

    fun addAccountsToList(listId: String, accountIds: List<String>): Completable = Completable.fromAction {
        listMethods.addAccountsToList(listId, accountIds)
    }

    fun deleteAccountsFromList(listId: String, accountIds: List<String>): Completable = Completable.fromAction {
        listMethods.deleteAccountsFromList(listId, accountIds)
    }
}
