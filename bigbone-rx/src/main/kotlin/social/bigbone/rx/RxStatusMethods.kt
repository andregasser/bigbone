package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.PreviewCard
import social.bigbone.api.entity.Context
import social.bigbone.api.entity.Status
import social.bigbone.api.method.StatusMethods

class RxStatusMethods(client: MastodonClient) {
    private val statusMethods = StatusMethods(client)

    fun getStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.getStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getContext(statusId: String): Single<Context> {
        return Single.create {
            try {
                val context = statusMethods.getContext(statusId)
                it.onSuccess(context.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getCard(statusId: String): Single<PreviewCard> {
        return Single.create {
            try {
                val context = statusMethods.getCard(statusId)
                it.onSuccess(context.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getRebloggedBy(statusId: String, range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = statusMethods.getRebloggedBy(statusId, range)
                it.onSuccess(accounts.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    //  GET /api/v1/favourited_by
    fun getFavouritedBy(statusId: String, range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = statusMethods.getFavouritedBy(statusId, range)
                it.onSuccess(accounts.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postStatus(
        status: String,
        inReplyToId: String? = null,
        mediaIds: List<String>? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        visibility: Status.Visibility = Status.Visibility.Public
    ): Single<Status> {
        return Single.create {
            try {
                val result = statusMethods.postStatus(status, inReplyToId, mediaIds, sensitive, spoilerText, visibility)
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun deleteStatus(statusId: String): Completable {
        return Completable.create {
            try {
                statusMethods.deleteStatus(statusId)
                it.onComplete()
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun reblogStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.reblogStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun unreblogStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.unreblogStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun favouriteStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.favouriteStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun unfavouriteStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.unfavouriteStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
