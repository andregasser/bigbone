package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a status ID that, if matched, should cause the filter action to be taken.
 * @see <a href="https://docs.joinmastodon.org/entities/FilterStatus/">Mastodon API FilterStatus</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class FilterStatus(
    /**
     * The ID of the FilterStatus in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The ID of the Status that will be filtered.
     */
    @SerialName("status_id")
    val statusId: String = ""
)
