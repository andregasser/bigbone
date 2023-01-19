package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Relationship
import social.bigbone.api.entity.Status
import social.bigbone.api.method.AccountsMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxAccountsMethods(client: MastodonClient) {
    private val accountsMethods = AccountsMethods(client)

    fun getAccount(accountId: String): Single<Account> {
        return Single.create {
            try {
                val account = accountsMethods.getAccount(accountId).execute()
                it.onSuccess(account)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun verifyCredentials(): Single<Account> {
        return Single.create {
            try {
                val credential = accountsMethods.verifyCredentials().execute()
                it.onSuccess(credential)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun updateCredentials(displayName: String?, note: String?, avatar: String?, header: String?): Single<Account> {
        return Single.create {
            try {
                val credential = accountsMethods.updateCredentials(displayName, note, avatar, header).execute()
                it.onSuccess(credential)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getFollowers(accountId: String, range: Range): Single<Pageable<Account>> {
        return Single.create {
            try {
                val followers = accountsMethods.getFollowers(accountId, range).execute()
                it.onSuccess(followers)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getFollowing(accountId: String, range: Range): Single<Pageable<Account>> {
        return Single.create {
            try {
                val following = accountsMethods.getFollowing(accountId, range).execute()
                it.onSuccess(following)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getStatuses(accountId: String, onlyMedia: Boolean, range: Range): Single<Pageable<Status>> {
        return Single.create {
            try {
                val statuses = accountsMethods.getStatuses(accountId, onlyMedia, range = range).execute()
                it.onSuccess(statuses)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun followAccount(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accountsMethods.followAccount(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun unfollowAccount(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accountsMethods.unfollowAccount(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun blockAccount(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accountsMethods.blockAccount(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun unblockAccount(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accountsMethods.unblockAccount(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun muteAccount(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accountsMethods.muteAccount(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun unmuteAccount(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accountsMethods.unmuteAccount(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getRelationships(accountIds: List<String>): Single<List<Relationship>> {
        return Single.create {
            try {
                val relationships = accountsMethods.getRelationships(accountIds).execute()
                it.onSuccess(relationships)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun searchAccounts(query: String, limit: Int = 40): Single<List<Account>> {
        return Single.create {
            try {
                val accounts = accountsMethods.searchAccounts(query, limit).execute()
                it.onSuccess(accounts)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
