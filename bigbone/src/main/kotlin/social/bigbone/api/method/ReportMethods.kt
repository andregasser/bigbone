package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.entity.Report
import social.bigbone.api.entity.data.ReportCategory
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/reports" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/reports/">Mastodon reports API methods</a>
 */
class ReportMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/reports"

    /**
     * File a report.
     * @param accountId The ID of the account to report
     * @param forward To forward the report to the remote admin
     * @param statusIds List of ID strings for statuses to be reported
     * @param ruleIds To specify the IDs of the exact rules broken in case of violations
     * @param comment The reason for the report. Default maximum of 1000 characters
     * @param category To specify if you are looking for a specific category of report
     * @see <a href="https://docs.joinmastodon.org/methods/reports/#post">Mastodon API documentation: methods/reports/#post</a>
     */
    @JvmOverloads
    @Throws(BigBoneRequestException::class)
    fun fileReport(
        accountId: String,
        forward: Boolean = false,
        statusIds: List<String>? = emptyList(),
        ruleIds: List<Int>? = emptyList(),
        comment: String? = null,
        category: ReportCategory? = null
    ): MastodonRequest<Report> {
        return client.getMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.POST,
            parameters = buildParameters(accountId, statusIds, comment, forward, ruleIds, category)
        )
    }

    private fun buildParameters(
        accountId: String,
        statusIds: List<String>? = emptyList(),
        comment: String? = null,
        forward: Boolean = false,
        ruleIds: List<Int>? = emptyList(),
        category: ReportCategory? = null
    ): Parameters {
        return Parameters().apply {
            append("account_id", accountId)
            append("forward", forward)
            append("category", setCategoryCorrectly(ruleIds, category).name)
            if (!statusIds.isNullOrEmpty()) {
                append("status_ids", statusIds)
            }
            if (!comment.isNullOrEmpty() && comment.isNotBlank()) {
                append("comment", comment)
            }
            if (!ruleIds.isNullOrEmpty()) {
                append("ruleIds", ruleIds)
            }
        }
    }

    private fun setCategoryCorrectly(
        ruleIds: List<Int>? = emptyList(),
        category: ReportCategory? = null
    ): ReportCategory {
        return when {
            !ruleIds.isNullOrEmpty() -> ReportCategory.VIOLATION
            category == null -> ReportCategory.OTHER
            else -> category
        }
    }
}
