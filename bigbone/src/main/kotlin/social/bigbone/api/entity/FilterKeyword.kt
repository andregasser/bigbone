package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a keyword that, if matched, should cause the filter action to be taken.
 * @see <a href="https://docs.joinmastodon.org/entities/FilterKeyword/">Mastodon API FilterKeyword</a>
 */
@Serializable
data class FilterKeyword(
    /**
     * The ID of the FilterKeyword in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The phrase to be matched against.
     */
    @SerialName("keyword")
    val keyword: String = "",

    /**
     * Should the filter consider word boundaries?
     * @see <a href="https://docs.joinmastodon.org/api/guidelines/#filters">implementation guidelines for filters</a>
     */
    @SerialName("whole_word")
    val wholeWord: Boolean = false
)
