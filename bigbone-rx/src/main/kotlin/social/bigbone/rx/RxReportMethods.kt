package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Report
import social.bigbone.api.method.ReportMethods

/**
 * Reactive implementation of [ReportMethods].
 * Allows access to API methods with endpoints having an "api/vX/reports" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/reports/">Mastodon reports API methods</a>
 */
class RxReportMethods(client: MastodonClient) {
    private val reportMethods = ReportMethods(client)

    fun fileReport(accountId: String, statusIds: List<String>, comment: String): Single<Report> {
        return Single.create {
            try {
                val report = reportMethods.fileReport(accountId, statusIds, comment)
                it.onSuccess(report.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
