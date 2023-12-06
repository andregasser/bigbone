package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.TimelineMethods

/**
 * Reactive implementation of [TimelineMethods].
 * Allows access to API methods with endpoints having an "api/vX/timelines" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/timelines/">Mastodon timelines API methods</a>
 */
class RxTimelineMethods(client: MastodonClient) {
    private val timelineMethods = TimelineMethods(client)

    @JvmOverloads
    fun getHomeTimeline(range: Range = Range()): Single<Pageable<Status>> =
        Single.fromCallable { timelineMethods.getHomeTimeline(range).execute() }

    @JvmOverloads
    fun getListTimeline(
        listId: String,
        range: Range = Range()
    ): Single<Pageable<Status>> = Single.fromCallable { timelineMethods.getListTimeline(listId, range).execute() }

    @JvmOverloads
    fun getPublicTimeline(
        statusOrigin: TimelineMethods.StatusOrigin = TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE,
        range: Range = Range()
    ): Single<Pageable<Status>> =
        Single.fromCallable { timelineMethods.getPublicTimeline(statusOrigin, range).execute() }

    @JvmOverloads
    fun getTagTimeline(
        tag: String,
        statusOrigin: TimelineMethods.StatusOrigin = TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE,
        range: Range = Range()
    ): Single<Pageable<Status>> =
        Single.fromCallable { timelineMethods.getTagTimeline(tag, statusOrigin, range).execute() }
}
