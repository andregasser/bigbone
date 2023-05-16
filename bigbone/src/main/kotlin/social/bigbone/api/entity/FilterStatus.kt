package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a status ID that, if matched, should cause the filter action to be taken.
 * @see <a href="https://docs.joinmastodon.org/entities/FilterStatus/">Mastodon API FilterStatus</a>
 */
data class FilterStatus(
    /**
     * The ID of the FilterStatus in the database.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * The ID of the Status that will be filtered.
     */
    @SerializedName("status_id")
    val statusId: String = ""
)
