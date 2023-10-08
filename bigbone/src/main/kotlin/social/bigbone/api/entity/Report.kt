package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val actionTaken: Boolean = false
)
