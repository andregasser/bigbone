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
    val source: SourceSuggestion = SourceSuggestion.STAFF,

    /**
     * The account being recommended to follow.
     */
    @SerialName("account")
    val account: Account
) {

    /**
     * Represents a suggested account to follow and an associated reason for the suggestion.
     */
    @Serializable
    enum class SourceSuggestion {
        /**
         * This account was manually recommended by your administration team.
         */
        @SerialName("staff")
        STAFF,

        /**
         * You have interacted with this account previously.
         */
        @SerialName("past_interactions")
        PAST_INTERACTIONS,

        /**
         * This account has many reblogs, favourites, and active local followers within the last 30 days.
         */
        @SerialName("global")
        GLOBAL
    }
}
