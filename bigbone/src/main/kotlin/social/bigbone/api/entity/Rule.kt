package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a rule that server users should follow.
 * @see <a href="https://docs.joinmastodon.org/entities/Rule/">Mastodon API Rule</a>
 */
data class Rule(
    /**
     * An identifier for the rule.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * The rule to be followed.
     */
    @SerializedName("text")
    val text: String = ""
)
