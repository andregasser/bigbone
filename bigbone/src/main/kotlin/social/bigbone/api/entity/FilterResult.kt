package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a filter whose keywords matched a given status.
 * @see <a href="https://docs.joinmastodon.org/entities/FilterResult/">Mastodon API FilterResult</a>
 */
data class FilterResult(
    /**
     * The filter that was matched.
     */
    @SerializedName("filter")
    val filter: Filter,

    /**
     * The keyword within the filter that was matched.
     */
    @SerializedName("keyword_matches")
    val keywordMatches: List<String>? = null,

    /**
     * The status ID within the filter that was matched.
     */
    @SerializedName("status_matches")
    val statusMatches: String? = null
)
