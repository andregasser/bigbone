package social.bigbone.rx.admin

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.admin.AdminReport
import social.bigbone.api.entity.data.ReportCategory
import social.bigbone.api.method.InstanceMethods
import social.bigbone.api.method.admin.AdminReportMethods

/**
 * Reactive implementation of [AdminReportMethods].
 *
 * Perform moderation actions with reports.
 *
 * @see <a href="https://docs.joinmastodon.org/methods/admin/reports/">Mastodon admin/reports API methods</a>
 */
class RxAdminReportMethods(client: MastodonClient) {

    private val adminReportMethods = AdminReportMethods(client)

    /**
     * View information about all reports.
     *
     * @param range optional Range for the pageable return value.
     * @param resolved Filter for resolved reports?
     * @param filedByAccountId Filter for reports filed by this account.
     * @param targetingAccountId Filter for reports targeting this account.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/reports/#get">Mastodon API documentation: admin/reports/#get</a>
     */
    @JvmOverloads
    fun getAllReports(
        range: Range = Range(),
        resolved: Boolean? = null,
        filedByAccountId: String? = null,
        targetingAccountId: String? = null
    ): Single<Pageable<AdminReport>> = Single.fromCallable {
        adminReportMethods.getAllReports(
            range = range,
            resolved = resolved,
            filedByAccountId = filedByAccountId,
            targetingAccountId = targetingAccountId
        ).execute()
    }

    /**
     * View information about a single report.
     *
     * @param reportId The ID of the [AdminReport] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/reports/#get-one">Mastodon API documentation: admin/reports/#get-one</a>
     */
    fun getReport(reportId: String): Single<AdminReport> = Single.fromCallable {
        adminReportMethods.getReport(reportId).execute()
    }

    /**
     * Change metadata for a report.
     *
     * @param reportId The ID of the [AdminReport] in the database.
     * @param updatedCategory Change the classification of the report to one of [ReportCategory].
     * @param violationRuleIds For [ReportCategory.VIOLATION] category reports, specify the ID of the exact rules broken.
     * Rules and their IDs are available via  [InstanceMethods.getRules] and [InstanceMethods.getInstance].
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/reports/#update">Mastodon API documentation: admin/reports/#update</a>
     */
    @JvmOverloads
    fun updateReportMetadata(
        reportId: String,
        updatedCategory: ReportCategory,
        violationRuleIds: List<Int>? = null
    ): Single<MastodonRequest<AdminReport>> = Single.fromCallable {
        adminReportMethods.updateReportMetadata(
            reportId = reportId,
            updatedCategory = updatedCategory,
            violationRuleIds = violationRuleIds
        )
    }

    /**
     * Claim the handling of this report to yourself.
     *
     * @param reportId The ID of the [AdminReport] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/reports/#assign_to_self">Mastodon API documentation: admin/reports/#assign_to_self</a>
     */
    fun assignReportToSelf(reportId: String): Single<AdminReport> = Single.fromCallable {
        adminReportMethods.assignReportToSelf(reportId).execute()
    }

    /**
     * Unassign a report so that someone else can claim it.
     *
     * @param reportId The ID of the [AdminReport] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/reports/#unassign">Mastodon API documentation: admin/reports/#unassign</a>
     */
    fun unassignReport(reportId: String): Single<AdminReport> = Single.fromCallable {
        adminReportMethods.unassignReport(reportId).execute()
    }

    /**
     * Mark a report as resolved with no futher action taken.
     *
     * @param reportId The ID of the [AdminReport] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/reports/#resolve">Mastodon API documentation: admin/reports/#resolve</a>
     */
    fun resolveReport(reportId: String): Single<AdminReport> = Single.fromCallable {
        adminReportMethods.resolveReport(reportId).execute()
    }

    /**
     * Reopen a currently closed report, if it is closed.
     *
     * @param reportId The ID of the [AdminReport] in the database.
     *
     * @see <a href="https://docs.joinmastodon.org/methods/admin/reports/#reopen">Mastodon API documentation: admin/reports/#reopen</a>
     */
    fun reopenReport(reportId: String): Single<AdminReport> = Single.fromCallable {
        adminReportMethods.reopenReport(reportId).execute()
    }
}
