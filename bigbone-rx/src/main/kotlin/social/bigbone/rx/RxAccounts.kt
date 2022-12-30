package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Relationship
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Accounts
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxAccounts(client: MastodonClient) {
    val accounts = Accounts(client)

    fun getAccount(accountId: String): Single<Account> {
        return Single.create {
            try {
                val account = accounts.getAccount(accountId).execute()
                it.onSuccess(account)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getVerifyCredentials(): Single<Account> {
        return Single.create {
            try {
                val credential = accounts.getVerifyCredentials().execute()
                it.onSuccess(credential)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun updateCredential(displayName: String?, note: String?, avatar: String?, header: String?): Single<Account> {
        return Single.create {
            try {
                val credential = accounts.updateCredential(displayName, note, avatar, header).execute()
                it.onSuccess(credential)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getFollowers(accountId: String, range: Range): Single<Pageable<Account>> {
        return Single.create {
            try {
                val followers = accounts.getFollowers(accountId, range).execute()
                it.onSuccess(followers)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getFollowing(accountId: String, range: Range): Single<Pageable<Account>> {
        return Single.create {
            try {
                val following = accounts.getFollowing(accountId, range).execute()
                it.onSuccess(following)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getStatuses(accountId: String, onlyMedia: Boolean, range: Range): Single<Pageable<Status>> {
        return Single.create {
            try {
                val statuses = accounts.getStatuses(accountId, onlyMedia, range = range).execute()
                it.onSuccess(statuses)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postFollow(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postFollow(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postUnFollow(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postUnFollow(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postBlock(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postBlock(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postUnblock(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postUnblock(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postMute(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postMute(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun postUnmute(accountId: String): Single<Relationship> {
        return Single.create {
            try {
                val relationship = accounts.postUnmute(accountId).execute()
                it.onSuccess(relationship)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getRelationships(accountIds: List<String>): Single<List<Relationship>> {
        return Single.create {
            try {
                val relationships = accounts.getRelationships(accountIds).execute()
                it.onSuccess(relationships)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }

    fun getAccountSearch(query: String, limit: Int = 40): Single<List<Account>> {
        return Single.create {
            try {
                val accounts = accounts.getAccountSearch(query, limit).execute()
                it.onSuccess(accounts)
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}
