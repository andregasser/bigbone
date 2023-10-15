package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an error message.
 * @see <a href="https://docs.joinmastodon.org/entities/Error/">Mastodon API Error</a>
 */
@Serializable
data class Error(
    /**
     * The error message.
     */
    @SerialName("error")
    val error: String = "",

    /**
     * A longer description of the error, mainly provided with the OAuth API.
     */
    @SerialName("error_description")
    val errorDescription: String? = null
)
