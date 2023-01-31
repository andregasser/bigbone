package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents an error message.
 * @see <a href="https://docs.joinmastodon.org/entities/Error/">Mastodon API Error</a>
 */
data class Error(
    /**
     * The error message.
     */
    @SerializedName("error")
    val error: String = ""
)
