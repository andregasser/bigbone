package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Context
import social.bigbone.api.entity.ScheduledStatus
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.StatusSource
import social.bigbone.api.entity.Translation
import social.bigbone.api.method.StatusMethods

/**
 * Reactive implementation of [StatusMethods].
 * Allows access to API methods with endpoints having an "api/vX/statuses" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/statuses/">Mastodon statuses API methods</a>
 */
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

    @JvmOverloads
    fun translateStatus(statusId: String, language: String? = null): Single<Translation> {
        return Single.create {
            try {
                val translation = statusMethods.translateStatus(statusId, language)
                it.onSuccess(translation.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    @JvmOverloads
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

    @JvmOverloads
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

    @JvmOverloads
    fun postStatus(
        status: String,
        visibility: Status.Visibility = Status.Visibility.Public,
        inReplyToId: String? = null,
        mediaIds: List<String>? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<Status> {
        return Single.create {
            try {
                val result = statusMethods.postStatus(status, visibility, inReplyToId, mediaIds, sensitive, spoilerText, language)
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    @JvmOverloads
    fun postPoll(
        status: String,
        pollOptions: List<String>,
        pollExpiresIn: Int,
        visibility: Status.Visibility = Status.Visibility.Public,
        pollMultiple: Boolean = false,
        pollHideTotals: Boolean = false,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<Status> {
        return Single.create {
            try {
                val result = statusMethods.postPoll(
                    status,
                    pollOptions,
                    pollExpiresIn,
                    visibility,
                    pollMultiple,
                    pollHideTotals,
                    inReplyToId,
                    sensitive,
                    spoilerText,
                    language
                )
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    @JvmOverloads
    fun scheduleStatus(
        status: String,
        scheduledAt: String,
        visibility: Status.Visibility = Status.Visibility.Public,
        inReplyToId: String? = null,
        mediaIds: List<String>? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<ScheduledStatus> {
        return Single.create {
            try {
                val result = statusMethods.scheduleStatus(status, scheduledAt, visibility, inReplyToId, mediaIds, sensitive, spoilerText, language)
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    @JvmOverloads
    fun schedulePoll(
        status: String,
        scheduledAt: String,
        pollOptions: List<String>,
        pollExpiresIn: Int,
        visibility: Status.Visibility = Status.Visibility.Public,
        pollMultiple: Boolean = false,
        pollHideTotals: Boolean = false,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<ScheduledStatus> {
        return Single.create {
            try {
                val result = statusMethods.schedulePoll(
                    status,
                    scheduledAt,
                    pollOptions,
                    pollExpiresIn,
                    visibility,
                    pollMultiple,
                    pollHideTotals,
                    inReplyToId,
                    sensitive,
                    spoilerText,
                    language
                )
                it.onSuccess(result.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun deleteStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.deleteStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    @JvmOverloads
    fun reblogStatus(statusId: String, visibility: Status.Visibility = Status.Visibility.Public): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.reblogStatus(statusId, visibility)
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

    fun bookmarkStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.bookmarkStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun unbookmarkStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.unbookmarkStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun muteConversation(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.muteConversation(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun unmuteConversation(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.unmuteConversation(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun pinStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.pinStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun unpinStatus(statusId: String): Single<Status> {
        return Single.create {
            try {
                val status = statusMethods.unpinStatus(statusId)
                it.onSuccess(status.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun getStatusSource(statusId: String): Single<StatusSource> {
        return Single.create {
            try {
                val statusSource = statusMethods.getStatusSource(statusId)
                it.onSuccess(statusSource.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
