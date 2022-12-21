package social.bigbone.rx

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Status
import social.bigbone.api.method.Timelines
import social.bigbone.rx.extensions.single

class RxTimelines(client: MastodonClient) {
    val timelines = Timelines(client)

    fun getHome(range: Range = Range()): Single<Pageable<Status>> {
        return single {
            timelines.getHome(range).execute()
        }
    }
}
