package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Context
import social.bigbone.api.entity.ScheduledStatus
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.StatusEdit
import social.bigbone.api.entity.StatusSource
import social.bigbone.api.entity.Translation
import social.bigbone.api.entity.data.PollData
import social.bigbone.api.entity.data.Visibility
import social.bigbone.api.method.StatusMethods

/**
 * Reactive implementation of [StatusMethods].
 * Allows access to API methods with endpoints having an "api/vX/statuses" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/statuses/">Mastodon statuses API methods</a>
 */
class RxStatusMethods(client: MastodonClient) {

    private val statusMethods = StatusMethods(client)

    fun getStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.getStatus(statusId).execute()
    }

    fun getContext(statusId: String): Single<Context> = Single.fromCallable {
        statusMethods.getContext(statusId).execute()
    }

    @JvmOverloads
    fun translateStatus(statusId: String, language: String? = null): Single<Translation> = Single.fromCallable {
        statusMethods.translateStatus(statusId, language).execute()
    }

    @JvmOverloads
    fun getRebloggedBy(statusId: String, range: Range = Range()): Single<Pageable<Account>> = Single.fromCallable {
        statusMethods.getRebloggedBy(statusId, range).execute()
    }

    @JvmOverloads
    fun getFavouritedBy(statusId: String, range: Range = Range()): Single<Pageable<Account>> = Single.fromCallable {
        statusMethods.getFavouritedBy(statusId, range).execute()
    }

    @JvmOverloads
    fun postStatus(
        status: String,
        mediaIds: List<String>? = null,
        visibility: Visibility = Visibility.PUBLIC,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<Status> = Single.fromCallable {
        statusMethods.postStatus(
            status,
            mediaIds,
            visibility,
            inReplyToId,
            sensitive,
            spoilerText,
            language
        ).execute()
    }

    @JvmOverloads
    fun postPoll(
        status: String,
        pollData: PollData,
        visibility: Visibility = Visibility.PUBLIC,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<Status> = Single.fromCallable {
        statusMethods.postPoll(
            status,
            pollData,
            visibility,
            inReplyToId,
            sensitive,
            spoilerText,
            language
        ).execute()
    }

    @JvmOverloads
    fun scheduleStatus(
        status: String,
        mediaIds: List<String>? = null,
        scheduledAt: String,
        visibility: Visibility = Visibility.PUBLIC,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<ScheduledStatus> = Single.fromCallable {
        statusMethods.scheduleStatus(
            status,
            mediaIds,
            scheduledAt,
            visibility,
            inReplyToId,
            sensitive,
            spoilerText,
            language
        ).execute()
    }

    @JvmOverloads
    fun schedulePoll(
        status: String,
        scheduledAt: String,
        pollData: PollData,
        visibility: Visibility = Visibility.PUBLIC,
        inReplyToId: String? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<ScheduledStatus> = Single.fromCallable {
        statusMethods.schedulePoll(
            status,
            scheduledAt,
            pollData,
            visibility,
            inReplyToId,
            sensitive,
            spoilerText,
            language
        ).execute()
    }

    fun deleteStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.deleteStatus(statusId).execute()
    }

    @JvmOverloads
    fun reblogStatus(statusId: String, visibility: Visibility = Visibility.PUBLIC): Single<Status> =
        Single.fromCallable { statusMethods.reblogStatus(statusId, visibility).execute() }

    fun unreblogStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.unreblogStatus(statusId).execute()
    }

    fun favouriteStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.favouriteStatus(statusId).execute()
    }

    fun unfavouriteStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.unfavouriteStatus(statusId).execute()
    }

    fun bookmarkStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.bookmarkStatus(statusId).execute()
    }

    fun unbookmarkStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.unbookmarkStatus(statusId).execute()
    }

    fun muteConversation(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.muteConversation(statusId).execute()
    }

    fun unmuteConversation(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.unmuteConversation(statusId).execute()
    }

    fun pinStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.pinStatus(statusId).execute()
    }

    fun unpinStatus(statusId: String): Single<Status> = Single.fromCallable {
        statusMethods.unpinStatus(statusId).execute()
    }

    @JvmOverloads
    fun editStatus(
        statusId: String,
        status: String,
        mediaIds: List<String>? = null,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<Status> = Single.fromCallable {
        statusMethods.editStatus(
            statusId,
            status,
            mediaIds,
            sensitive,
            spoilerText,
            language
        ).execute()
    }

    @JvmOverloads
    fun editPoll(
        statusId: String,
        status: String,
        pollData: PollData,
        sensitive: Boolean = false,
        spoilerText: String? = null,
        language: String? = null
    ): Single<Status> = Single.fromCallable {
        statusMethods.editPoll(
            statusId,
            status,
            pollData,
            sensitive,
            spoilerText,
            language
        ).execute()
    }

    fun getEditHistory(statusId: String): Single<List<StatusEdit>> = Single.fromCallable {
        statusMethods.getEditHistory(statusId).execute()
    }

    fun getStatusSource(statusId: String): Single<StatusSource> = Single.fromCallable {
        statusMethods.getStatusSource(statusId).execute()
    }
}
