package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Timelines
import social.bigbone.rx.extensions.single

class RxTimelines(client: MastodonClient) {
    val timelines = Timelines(client)

    fun getHomeTimeline(range: Range = Range()): Single<Pageable<Status>> {
        return single {
            timelines.getHomeTimeline(range).execute()
        }
    }

    fun getPublicTimeline(
        range: Range = Range(),
        statusOrigin: Timelines.StatusOrigin = Timelines.StatusOrigin.LOCAL_AND_REMOTE
    ): Single<Pageable<Status>> {
        return single {
            timelines.getPublicTimeline(range, statusOrigin).execute()
        }
    }

    fun getTagTimeline(
        tag: String,
        range: Range = Range(),
        statusOrigin: Timelines.StatusOrigin = Timelines.StatusOrigin.LOCAL_AND_REMOTE
    ): Single<Pageable<Status>> {
        return single {
            timelines.getTagTimeline(tag, range, statusOrigin).execute()
        }
    }
}
