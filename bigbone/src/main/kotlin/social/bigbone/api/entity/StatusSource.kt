package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a status's source as plain text.
 * @see <a href="https://docs.joinmastodon.org/entities/StatusSource/">Mastodon API StatusSource</a>
 */
data class StatusSource(
    /**
     * ID of the status in the database.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * The plain text used to compose the status.
     */
    @SerializedName("text")
    val text: String = "",

    /**
     * The plain text used to compose the status’s subject or content warning.
     */
    @SerializedName("spoiler_text")
    val spoilerText: String = ""
)
