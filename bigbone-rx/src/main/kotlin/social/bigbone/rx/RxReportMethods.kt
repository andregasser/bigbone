package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Report
import social.bigbone.api.entity.data.ReportCategory
import social.bigbone.api.method.ReportMethods

/**
 * Reactive implementation of [ReportMethods].
 * Allows access to API methods with endpoints having an "api/vX/reports" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/reports/">Mastodon reports API methods</a>
 */
class RxReportMethods(client: MastodonClient) {

    private val reportMethods = ReportMethods(client)

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
    fun fileReport(
        accountId: String,
        forward: Boolean = false,
        statusIds: List<String>? = emptyList(),
        ruleIds: List<Int>? = emptyList(),
        comment: String? = null,
        category: ReportCategory? = null
    ): Single<Report> {
        return Single.fromCallable {
            reportMethods.fileReport(accountId, forward, statusIds, ruleIds, comment, category).execute()
        }
    }
}
