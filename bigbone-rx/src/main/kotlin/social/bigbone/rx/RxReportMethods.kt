package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Report
import social.bigbone.api.entity.Report.ReportCategory
import social.bigbone.api.method.ReportMethods

/**
 * Reactive implementation of [ReportMethods].
 * Allows access to API methods with endpoints having an "api/vX/reports" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/reports/">Mastodon reports API methods</a>
 */
class RxReportMethods(client: MastodonClient) {
    private val reportMethods = ReportMethods(client)

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
