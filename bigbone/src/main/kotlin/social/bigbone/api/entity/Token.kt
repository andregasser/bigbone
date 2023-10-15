package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an OAuth token used for authenticating with the API and performing actions.
 * @see <a href="https://docs.joinmastodon.org/entities/Token/">Mastodon API Token</a>
 */
@Serializable
data class Token(
    @SerialName("access_token")
    val accessToken: String = "",

    /**
     * The OAuth token type. Mastodon uses Bearer tokens.
     */
    @SerialName("token_type")
    val tokenType: String = "",

    /**
     * The OAuth scopes granted by this token, space-separated.
     */
    @SerialName("scope")
    val scope: String = "",

    /**
     * When the token was generated.
     */
    @SerialName("created_at")
    val createdAt: Long = 0L
)
