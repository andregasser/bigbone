package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.TimelineMethods
import social.bigbone.rx.extensions.single

/**
 * Reactive implementation of [TimelineMethods].
 * Allows access to API methods with endpoints having an "api/vX/timelines" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/timelines/">Mastodon timelines API methods</a>
 */
class RxTimelineMethods(client: MastodonClient) {
    private val timelineMethods = TimelineMethods(client)

    fun getHomeTimeline(range: Range = Range()): Single<Pageable<Status>> {
        return single {
            timelineMethods.getHomeTimeline(range).execute()
        }
    }

    fun getPublicTimeline(
        range: Range = Range(),
        statusOrigin: TimelineMethods.StatusOrigin = TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE
    ): Single<Pageable<Status>> {
        return single {
            timelineMethods.getPublicTimeline(range, statusOrigin).execute()
        }
    }

    fun getTagTimeline(
        tag: String,
        range: Range = Range(),
        statusOrigin: TimelineMethods.StatusOrigin = TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE
    ): Single<Pageable<Status>> {
        return single {
            timelineMethods.getTagTimeline(tag, range, statusOrigin).execute()
        }
    }
}
