package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameter
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Report
import social.bigbone.api.exception.BigboneRequestException

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#reports
 */
class Reports(private val client: MastodonClient) {
    // GET /api/v1/reports
    @JvmOverloads
    @Throws(BigboneRequestException::class)
    fun getReports(range: Range = Range()): MastodonRequest<Pageable<Report>> {
        return MastodonRequest<Pageable<Report>>(
            {
                client.get(
                    "api/v1/reports",
                    range.toParameter()
                )
            },
            {
                client.getSerializer().fromJson(it, Report::class.java)
            }
        ).toPageable()
    }

    /**
     * POST /api/v1/reports
     * account_id: The ID of the account to report
     * status_ids: The IDs of statuses to report (can be an array)
     * comment: A comment to associate with the report.
     */
    @Throws(BigboneRequestException::class)
    fun postReport(accountId: String, statusId: Long, comment: String): MastodonRequest<Report> {
        val parameters = Parameter().apply {
            append("account_id", accountId)
            append("status_ids", statusId)
            append("comment", comment)
        }
        return MastodonRequest<Report>(
            {
                client.post("api/v1/reports", parameters)
            },
            {
                client.getSerializer().fromJson(it, Report::class.java)
            }
        )
    }
}
