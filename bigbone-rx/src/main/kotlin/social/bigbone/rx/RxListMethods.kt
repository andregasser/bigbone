package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.MastodonList
import social.bigbone.api.method.ListMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

/**
 * Reactive implementation of [ListMethods].
 * Allows access to API methods with endpoints having an "api/vX/lists" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/lists/">Mastodon lists API methods</a>
 */
class RxListMethods(client: MastodonClient) {
    private val listMethods = ListMethods(client)

    fun getLists(): Single<List<MastodonList>> {
        return Single.create {
            try {
                val lists = listMethods.getLists()
                it.onSuccess(lists.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getList(listId: String): Single<MastodonList> {
        return Single.create {
            try {
                val list = listMethods.getList(listId)
                it.onSuccess(list.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    @JvmOverloads
    fun createList(
        title: String,
        repliesPolicy: MastodonList.RepliesPolicy = MastodonList.RepliesPolicy.List
    ): Single<MastodonList> {
        return Single.create {
            try {
                val list = listMethods.createList(title, repliesPolicy)
                it.onSuccess(list.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun updateList(
        listId: String,
        title: String,
        repliesPolicy: MastodonList.RepliesPolicy
    ): Single<MastodonList> {
        return Single.create {
            try {
                val list = listMethods.updateList(listId, title, repliesPolicy)
                it.onSuccess(list.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun deleteList(listId: String): Completable {
        return Completable.create {
            try {
                listMethods.deleteList(listId)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    @JvmOverloads
    fun getAccountsInList(listId: String, range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = listMethods.getAccountsInList(listId, range)
                it.onSuccess(accounts.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun addAccountsToList(listId: String, accountIds: List<String>): Completable {
        return Completable.create {
            try {
                listMethods.addAccountsToList(listId, accountIds)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun deleteAccountsFromList(listId: String, accountIds: List<String>): Completable {
        return Completable.create {
            try {
                listMethods.deleteAccountsFromList(listId, accountIds)
                it.onComplete()
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
