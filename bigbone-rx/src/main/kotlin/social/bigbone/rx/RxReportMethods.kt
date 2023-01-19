package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Report
import social.bigbone.api.method.ReportMethods

class RxReportMethods(client: MastodonClient) {
    private val reportMethods = ReportMethods(client)

    fun getReports(range: Range = Range()): Single<Pageable<Report>> {
        return Single.create {
            try {
                val reports = reportMethods.getReports(range)
                it.onSuccess(reports.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun fileReport(accountId: String, statusId: String, comment: String): Single<Report> {
        return Single.create {
            try {
                val report = reportMethods.fileReport(accountId, statusId, comment)
                it.onSuccess(report.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
