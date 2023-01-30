package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Report
import social.bigbone.api.method.ReportMethods

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
