package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.entity.admin.AdminCohort
import social.bigbone.api.entity.admin.AdminCohort.FrequencyOneOf
import social.bigbone.api.method.admin.AdminRetentionMethods
import java.time.Instant

/**
 * Reactive implementation of [AdminRetentionMethods].
 *
 * Show retention data over time.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/retention/">Mastodon admin/retention API methods</a>
 */
class RxAdminRetentionMethods(private val client: MastodonClient) {

    private val adminRetentionMethods = AdminRetentionMethods(client)

    /**
     * Generate a retention data report for a given time period and bucket.
     *
     * @param startAt The start date for the time period. If a time is provided, it will be ignored.
     * @param endAt The end date for the time period. If a time is provided, it will be ignored.
     * @param frequency Specify whether to use [FrequencyOneOf.DAY] or [FrequencyOneOf.MONTH] buckets.
     * @see <a href="https://docs.joinmastodon.org/methods/admin/retention/#create">Mastodon API documentation: admin/retention/#create</a>
     */
    fun calculateRetentionData(
        startAt: Instant,
        endAt: Instant,
        frequency: FrequencyOneOf
    ): Single<MastodonRequest<List<AdminCohort>>> = Single.fromCallable {
        adminRetentionMethods.calculateRetentionData(
            startAt = startAt,
            endAt = endAt,
            frequency = frequency
        )
    }
}
