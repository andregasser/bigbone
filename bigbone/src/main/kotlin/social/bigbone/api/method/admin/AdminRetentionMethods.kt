package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.admin.AdminCohort
import social.bigbone.api.entity.admin.AdminCohort.FrequencyOneOf
import java.time.Instant

/**
 * Show retention data over time.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/retention/">Mastodon admin/retention API methods</a>
 */
class AdminRetentionMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/admin/retention"

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
    ): MastodonRequest<List<AdminCohort>> {
        return client.getMastodonRequestForList(
            endpoint = endpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("start_at", startAt.toString())
                append("end_at", endAt.toString())
                append("frequency", frequency.apiName)
            }
        )
    }
}
