package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Report
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/reports" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/reports/">Mastodon reports API methods</a>
 */
class ReportsMethods(private val client: MastodonClient) {
    // GET /api/v1/reports
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun getReports(range: Range = Range()): MastodonRequest<Pageable<Report>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/reports",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter()
        )
    }

    /**
     * POST /api/v1/reports
     * account_id: The ID of the account to report
     * status_ids: The IDs of statuses to report (can be an array)
     * comment: A comment to associate with the report.
     */
    @Throws(BigBoneRequestException::class)
    fun fileReport(accountId: String, statusId: String, comment: String): MastodonRequest<Report> {
        return client.getMastodonRequest(
            endpoint = "api/v1/reports",
            method = MastodonClient.Method.POST,
            parameters = Parameter().apply {
                append("account_id", accountId)
                append("status_ids", statusId)
                append("comment", comment)
            }
        )
    }
}
