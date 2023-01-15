package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Report
import social.bigbone.api.method.Reports

class RxReports(client: MastodonClient) {
    val reports = Reports(client)

    fun getReports(range: Range = Range()): Single<Pageable<Report>> {
        return Single.create {
            try {
                val reports = reports.getReports(range)
                it.onSuccess(reports.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    fun fileReport(accountId: String, statusId: String, comment: String): Single<Report> {
        return Single.create {
            try {
                val report = reports.fileReport(accountId, statusId, comment)
                it.onSuccess(report.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
