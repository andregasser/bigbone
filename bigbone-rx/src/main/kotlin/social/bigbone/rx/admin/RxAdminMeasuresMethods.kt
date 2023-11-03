package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Admin
import social.bigbone.api.method.admin.AdminMeasuresMethods
import java.time.Instant

class RxAdminMeasuresMethods(client: MastodonClient) {

    private val adminMeasuresMethods = AdminMeasuresMethods(client)

    /**
     * Obtain statistical measures for your server.
     *
     * @param startAt The start date for the time period. If a time is provided, it will be ignored.
     * @param endAt The end date for the time period. If a time is provided, it will be ignored.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/measures/#get">Mastodon API documentation: admin/measures/#get</a>
     */
    fun getMeasurableDate(
        startAt: Instant,
        endAt: Instant,
    ): Single<List<Admin.Measure>> = Single.fromCallable {
        adminMeasuresMethods.getMeasurableDate(
            startAt = startAt,
            endAt = endAt
        ).execute()
    }
}
