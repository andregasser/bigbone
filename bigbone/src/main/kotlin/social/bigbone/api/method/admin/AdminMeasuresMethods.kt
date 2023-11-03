package social.bigbone.api.method.admin

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.Admin
import java.time.Instant

/**
 * Obtain quantitative metrics about the server.
 * @see <a href="https://docs.joinmastodon.org/methods/admin/measures/">Mastodon admin/measures API methods</a>
 */
class AdminMeasuresMethods(private val client: MastodonClient) {

    private val adminMeasuresEndpoint = "api/v1/admin/measures"

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
    ): MastodonRequest<List<Admin.Measure>> {
        return client.getMastodonRequestForList(
            endpoint = adminMeasuresEndpoint,
            method = MastodonClient.Method.POST,
            parameters = Parameters().apply {
                append("start_at", startAt.toString())
                append("end_at", endAt.toString())
            }
        )
    }
}
