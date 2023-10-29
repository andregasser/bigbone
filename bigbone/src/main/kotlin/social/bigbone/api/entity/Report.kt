package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.api.entity.Report.ReportType

/**
 * Reports filed against users and/or statuses, to be taken action on by moderators.
 * @see <a href="https://docs.joinmastodon.org/entities/Report/">Mastodon API Report</a>
 */
@Serializable
data class Report(
    /**
     * The ID of the report in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * Whether an action was taken yet.
     */
    @SerialName("action_taken")
    val actionTaken: Boolean = false,

    /**
     * When an action was taken against the report. (ISO 8601 Datetime)
     */
    @SerialName("action_taken_at")
    @Serializable(with = DateTimeSerializer::class)
    val actionTakenAt: PrecisionDateTime = PrecisionDateTime.Unavailable,

    /**
     * The generic reason for the report.
     * @see ReportType
     */
    @SerialName("category")
    val category: String = ReportType.OTHER.name,

    /**
     * The reason for the report.
     */
    @SerialName("comment")
    val comment: String = "",

    /**
     * Whether the report was forwarded to a remote domain.
     */
    @SerialName("forwarded")
    val forwarded: Boolean = false,

    /**
     * When the report was created. (ISO 8601 Datetime)
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.Unavailable,

    /**
     * IDs of statuses that have been attached to this report for additional context.
     */
    @SerialName("status_ids")
    val statusIds: List<String>? = null,

    /**
     * IDs of the rules that have been cited as a violation by this report.
     */
    @SerialName("rule_ids")
    val ruleIds: List<String>? = null,

    /**
     * The account that was reported.
     */
    @SerialName("target_account")
    val targetAccount: Account
) {
    /**
     * Specify the typology of category among spam, violation or other.
     */
    enum class ReportType(val type: String) {
        SPAM("spam"),
        VIOLATION("violation"),
        OTHER("other")
    }
}
