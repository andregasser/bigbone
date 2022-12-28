package social.bigbone.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Card
import social.bigbone.api.entity.Context
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Statuses

class RxStatuses(client: MastodonClient) {
    val statuses = Statuses(client)

    fun getStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statuses.getStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getContext(statusId: String): Single<Context> {
        return Single.create {
            try {
                val context = statuses.getContext(statusId)
                it.onSuccess(context.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getCard(statusId: String): Single<Card> {
        return Single.create {
            try {
                val context = statuses.getCard(statusId)
                it.onSuccess(context.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getRebloggedBy(statusId: String, range: Range = Range()): Single<Pageable<Account>> {
        return Single.create {
            try {
                val accounts = statuses.getRebloggedBy(statusId, range)
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
                val accounts = statuses.getFavouritedBy(statusId, range)
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
                val result = statuses.postStatus(status, inReplyToId, mediaIds, sensitive, spoilerText, visibility)
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun deleteStatus(statusId: String): Completable {
        return Completable.create {
            try {
                statuses.deleteStatus(statusId)
                it.onComplete()
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postReblog(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statuses.postReblog(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postUnreblog(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statuses.postUnreblog(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postFavourite(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statuses.postFavourite(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun postUnfavourite(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statuses.postUnfavourite(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
