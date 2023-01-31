package social.bigbone.api.entity.auth

import com.google.gson.annotations.SerializedName

/**
 * Represents an OAuth token used for authenticating with the API and performing actions.
 * @see <a href="https://docs.joinmastodon.org/entities/Token/">Mastodon API Token</a>
 */
data class AccessToken(
    /**
     * An OAuth token to be used for authorization.
     */
    @SerializedName("access_token")
    val accessToken: String = "",

    /**
     * The OAuth token type. Mastodon uses Bearer tokens.
     */
    @SerializedName("token_type")
    val tokenType: String = "",

    /**
     * The OAuth scopes granted by this token, space-separated.
     */
    @SerializedName("scope")
    val scope: String = "",

    /**
     * When the token was generated.
     */
    @SerializedName("created_at")
    val createdAt: Long = 0L
)
