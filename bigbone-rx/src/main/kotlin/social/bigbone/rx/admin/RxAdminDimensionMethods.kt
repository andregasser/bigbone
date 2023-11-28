package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.admin.AdminDimension
import social.bigbone.api.method.admin.AdminDimensionMethods
import social.bigbone.api.method.admin.RequestDimension
import java.time.Instant

/**
 * Reactive implementation of [AdminDimensionMethods].
 *
 * Obtain qualitative metrics about the server.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/dimensions/">Mastodon admin/dimensions API methods</a>
 */
class RxAdminDimensionMethods(client: MastodonClient) {

    private val adminDimensionMethods = AdminDimensionMethods(client)

    /**
     * Obtain information about popularity of certain accounts, servers, languages, etc.
     *
     * @param dimensions Request specific dimensions. Uses helper wrapper [RequestDimension] to ensure that
     * required fields are set for any given [AdminDimension.Key].
     * @param startAt The start date for the time period. If a time is provided, it will be ignored.
     * @param endAt The end date for the time period. If a time is provided, it will be ignored.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/dimensions/#get">Mastodon API documentation: admin/dimensions/#get</a>
     */
    fun getDimensionalData(
        dimensions: List<RequestDimension>,
        startAt: Instant,
        endAt: Instant
    ): Single<List<AdminDimension>> = Single.fromCallable {
        adminDimensionMethods.getDimensionalData(
            dimensions = dimensions,
            startAt = startAt,
            endAt = endAt
        ).execute()
    }
}
