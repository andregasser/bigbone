package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Mentions of users within the status content.
 * @see <a href="https://docs.joinmastodon.org/entities/Status/#Mention">Mastodon API Status::Mention</a>
 */
data class StatusMention(
    /**
     * The location of the mentioned user’s profile.
     */
    @SerializedName("url")
    val url: String = "",

    /**
     * The username of the mentioned user.
     */
    @SerializedName("username")
    val username: String = "",

    /**
     * The webfinger acct: URI of the mentioned user. Equivalent to username for local users, or username@domain for remote users.
     */
    @SerializedName("acct")
    val acct: String = "",

    /**
     * The account ID of the mentioned user.
     */
    @SerializedName("id")
    val id: String = "0"
)
