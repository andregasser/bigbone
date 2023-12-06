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

    /**
     * Fetch all lists that the user owns.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#get">Mastodon API documentation: methods/lists/#get</a>
     */
    fun getLists(): Single<List<MastodonList>> = Single.fromCallable { listMethods.getLists().execute() }

    /**
     * Fetch the list with the given ID. Used for verifying the title of a list, and which replies to show within that list.
     * @param listId The ID of the List in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#get-one">Mastodon API documentation: methods/lists/#get-one</a>
     */
    fun getList(listId: String): Single<MastodonList> = Single.fromCallable { listMethods.getList(listId).execute() }

    /**
     * Create a new list.
     * @param title The title of the list to be created.
     * @param repliesPolicy One of [MastodonList.RepliesPolicy], defaults to [MastodonList.RepliesPolicy.List].
     * @param exclusive Whether members of this list need to get removed from the “Home” feed.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#create">Mastodon API documentation: methods/lists/#create</a>
     */
    @JvmOverloads
    fun createList(
        title: String,
        repliesPolicy: MastodonList.RepliesPolicy = MastodonList.RepliesPolicy.LIST,
        exclusive: Boolean? = null
    ): Single<MastodonList> = Single.fromCallable { listMethods.createList(title, repliesPolicy, exclusive).execute() }

    /**
     * Change the title of a list, or which replies to show.
     * @param listId The ID of the List in the database.
     * @param title The new title of the list to be updated.
     * @param repliesPolicy One of [MastodonList.RepliesPolicy].
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#update">Mastodon API documentation: methods/lists/#update</a>
     */
    fun updateList(
        listId: String,
        title: String,
        repliesPolicy: MastodonList.RepliesPolicy
    ): Single<MastodonList> = Single.fromCallable { listMethods.updateList(listId, title, repliesPolicy).execute() }

    /**
     * Delete a list.
     * @param listId The ID of the List in the database.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#delete">Mastodon API documentation: methods/lists/#delete</a>
     */
    fun deleteList(listId: String): Completable = Completable.fromAction { listMethods.deleteList(listId) }

    /**
     * View accounts in a list.
     * @param listId The ID of the List in the database.
     * @param range optional Range for the pageable return value
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#accounts">Mastodon API documentation: methods/lists/#accounts</a>
     */
    @JvmOverloads
    fun getAccountsInList(listId: String, range: Range = Range()): Single<Pageable<Account>> = Single.fromCallable {
        listMethods.getAccountsInList(listId, range).execute()
    }

    /**
     * Add accounts to the given list. Note that the user must be following these accounts.
     * @param listId The ID of the List in the database.
     * @param accountIds The accounts that should be added to the list.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#accounts-add">Mastodon API documentation: methods/lists/#accounts-add</a>
     */
    fun addAccountsToList(listId: String, accountIds: List<String>): Completable = Completable.fromAction {
        listMethods.addAccountsToList(listId, accountIds)
    }

    /**
     * Remove accounts from the given list.
     * @param listId The ID of the List in the database.
     * @param accountIds The accounts that should be removed from the list.
     * @see <a href="https://docs.joinmastodon.org/methods/lists/#accounts-remove">Mastodon API documentation: methods/lists/#accounts-remove</a>
     */
    fun deleteAccountsFromList(listId: String, accountIds: List<String>): Completable = Completable.fromAction {
        listMethods.deleteAccountsFromList(listId, accountIds)
    }
}
