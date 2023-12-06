package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a filter whose keywords matched a given status.
 * @see <a href="https://docs.joinmastodon.org/entities/FilterResult/">Mastodon API FilterResult</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class FilterResult(
    /**
     * The filter that was matched.
     */
    @SerialName("filter")
    val filter: Filter,

    /**
     * The keyword within the filter that was matched.
     */
    @SerialName("keyword_matches")
    val keywordMatches: List<String>? = null,

    /**
     * The status ID within the filter that was matched.
     */
    @SerialName("status_matches")
    val statusMatches: String? = null
)
