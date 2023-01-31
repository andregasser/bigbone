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
    val actionTaken: String = ""
)
