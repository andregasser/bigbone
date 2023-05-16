package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a keyword that, if matched, should cause the filter action to be taken.
 * @see <a href="https://docs.joinmastodon.org/entities/FilterKeyword/">Mastodon API FilterKeyword</a>
 */
data class FilterKeyword(
    /**
     * The ID of the FilterKeyword in the database.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * The phrase to be matched against.
     */
    @SerializedName("keyword")
    val keyword: String = "",

    /**
     * Should the filter consider word boundaries?
     * @see <a href="https://docs.joinmastodon.org/api/guidelines/#filters">implementation guidelines for filters</a>
     */
    @SerializedName("whole_word")
    val wholeWord: Boolean = false
)
