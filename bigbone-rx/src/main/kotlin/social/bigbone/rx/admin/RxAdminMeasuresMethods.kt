package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.admin.AdminMeasure
import social.bigbone.api.entity.admin.AdminMeasure.Key
import social.bigbone.api.method.admin.AdminMeasuresMethods
import social.bigbone.api.method.admin.RequestMeasure
import java.time.Instant

/**
 * Reactive implementation of [AdminMeasuresMethods].
 *
 * Obtain quantitative metrics about the server.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/measures/">Mastodon admin/measures API methods</a>
 */
class RxAdminMeasuresMethods(client: MastodonClient) {

    private val adminMeasuresMethods = AdminMeasuresMethods(client)

    /**
     * Obtain statistical measures for your server.
     *
     * @param measures Request specific measures. Uses helper wrapper [RequestMeasure] to ensure that required fields are set for any given [Key].
     * @param startAt The start date for the time period. If a time is provided, it will be ignored.
     * @param endAt The end date for the time period. If a time is provided, it will be ignored.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/measures/#get">Mastodon API documentation: admin/measures/#get</a>
     */
    fun getMeasurableDate(
        measures: List<RequestMeasure>,
        startAt: Instant,
        endAt: Instant
    ): Single<List<AdminMeasure>> = Single.fromCallable {
        adminMeasuresMethods.getMeasurableData(
            measures = measures,
            startAt = startAt,
            endAt = endAt
        ).execute()
    }
}
