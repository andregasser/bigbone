package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a rule that server users should follow.
 * @see <a href="https://docs.joinmastodon.org/entities/Rule/">Mastodon API Rule</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class Rule(
    /**
     * An identifier for the rule.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The rule to be followed.
     */
    @SerialName("text")
    val text: String = ""
)
