package social.bigbone.api.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.api.entity.Rule
import social.bigbone.api.entity.Status
import social.bigbone.api.entity.data.ReportCategory

/**
 * Admin-level information about a filed report.
 *
 * @see <a href="https://docs.joinmastodon.org/entities/Admin_Report/">Mastodon API Admin::Report</a>
 */
@Serializable
data class AdminReport(
    /**
     * The ID of the report in the database.
     * String cast from an integer, but not guaranteed to be a number.
     */
    @SerialName("id")
    val id: String,

    /**
     * Whether an action was taken to resolve this report.
     */
    @SerialName("action_taken")
    val actionTaken: Boolean,

    /**
     * When an action was taken, if this report is currently unresolved.
     */
    @SerialName("action_taken_at")
    @Serializable(with = DateTimeSerializer::class)
    val actionTakenAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * The category under which the report is classified.
     * @see ReportCategory
     */
    @SerialName("category")
    val category: ReportCategory,

    /**
     * An optional reason for reporting.
     */
    @SerialName("comment")
    val comment: String? = null,

    /**
     * Whether the report was forwarded to a remote instance.
     */
    @SerialName("forwarded")
    val forwarded: Boolean,

    /**
     * The time the report was filed.
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * The time of last action on this report.
     */
    @SerialName("updated_at")
    @Serializable(with = DateTimeSerializer::class)
    val updatedAt: PrecisionDateTime = PrecisionDateTime.InvalidPrecisionDateTime.Unavailable,

    /**
     * The account which filed the report.
     */
    @SerialName("account")
    val reportCreator: AdminAccount,

    /**
     * The account being reported.
     */
    @SerialName("target_account")
    val reportedAccount: AdminAccount,

    /**
     * The account of the moderator assigned to this report.
     * May be null if no moderator was assigned.
     */
    @SerialName("assigned_account")
    val assignedModerator: AdminAccount?,

    /**
     * The account of the moderator who handled the report.
     * May be null if no action has been taken on this report by any moderator yet.
     */
    @SerialName("action_taken_by_account")
    val handledBy: AdminAccount?,

    /**
     * Statuses attached to the report, for context.
     */
    @SerialName("statuses")
    val attachedStatuses: List<Status>,

    /**
     * Rules attached to the report, for context.
     */
    @SerialName("rules")
    val attachedRules: List<Rule>
)
