package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a suggested account to follow and an associated reason for the suggestion.
 * @see <a href="https://docs.joinmastodon.org/entities/Suggestion/">Mastodon API Suggestion</a>
 */

@Serializable
data class Suggestion(

    /**
     * The reason this account is being suggested.
     */
    @SerialName("source")
    val source: String = SourceSuggestion.STAFF.value,

    /**
     * The account being recommended to follow.
     */
    @SerialName("account")
    val account: Account
) {

    /**
     * Represents a suggested account to follow and an associated reason for the suggestion.
     */
    enum class SourceSuggestion(val value: String) {
        /**
         * This account was manually recommended by your administration team.
         */
        STAFF("staff"),

        /**
         * You have interacted with this account previously.
         */
        PAST_INTERACTIONS("past_interactions"),

        /**
         * This account has many reblogs, favourites, and active local followers within the last 30 days.
         */
        GLOBAL("global")
    }
}
