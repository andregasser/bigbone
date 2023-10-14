package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Reports filed against users and/or statuses, to be taken action on by moderators.
 * @see <a href="https://docs.joinmastodon.org/entities/Report/">Mastodon API Report</a>
 */
data class Report(
    /**
     * The ID of the report in the database.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * Whether an action was taken yet.
     */
    @SerializedName("action_taken")
    val actionTaken: Boolean = false,

    /**
     * When an action was taken against the report. (ISO 8601 Datetime)
     */
    @SerializedName("action_taken_at")
    val actionTakenAt: String? = "",

    /**
     * The generic reason for the report.
     * @see ReportType
     */
    @SerializedName("category")
    val category: String = ReportType.OTHER.name,

    /**
     * The reason for the report.
     */
    @SerializedName("comment")
    val comment: String = "",

    /**
     * Whether the report was forwarded to a remote domain.
     */
    @SerializedName("forwarded")
    val forwarded: Boolean = false,

    /**
     * When the report was created. (ISO 8601 Datetime)
     */
    @SerializedName("created_at")
    val createdAt: String = "",

    /**
     * IDs of statuses that have been attached to this report for additional context.
     */
    @SerializedName("status_ids")
    val statusIds: List<String>? = null,

    /**
     * IDs of the rules that have been cited as a violation by this report.
     */
    @SerializedName("rule_ids")
    val ruleIds: List<String>? = null,

    /**
     * The account that was reported.
     */
    @SerializedName("target_account")
    val targetAccount: Account,
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
