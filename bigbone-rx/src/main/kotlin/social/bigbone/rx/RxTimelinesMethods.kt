package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.TimelinesMethods
import social.bigbone.rx.extensions.single

class RxTimelinesMethods(client: MastodonClient) {
    val timelinesMethods = TimelinesMethods(client)

    fun getHomeTimeline(range: Range = Range()): Single<Pageable<Status>> {
        return single {
            timelinesMethods.getHomeTimeline(range).execute()
        }
    }

    fun getPublicTimeline(
        range: Range = Range(),
        statusOrigin: TimelinesMethods.StatusOrigin = TimelinesMethods.StatusOrigin.LOCAL_AND_REMOTE
    ): Single<Pageable<Status>> {
        return single {
            timelinesMethods.getPublicTimeline(range, statusOrigin).execute()
        }
    }

    fun getTagTimeline(
        tag: String,
        range: Range = Range(),
        statusOrigin: TimelinesMethods.StatusOrigin = TimelinesMethods.StatusOrigin.LOCAL_AND_REMOTE
    ): Single<Pageable<Status>> {
        return single {
            timelinesMethods.getTagTimeline(tag, range, statusOrigin).execute()
        }
    }
}
