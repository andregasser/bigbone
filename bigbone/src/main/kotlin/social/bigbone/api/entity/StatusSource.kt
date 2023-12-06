package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a status's source as plain text.
 * @see <a href="https://docs.joinmastodon.org/entities/StatusSource/">Mastodon API StatusSource</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class StatusSource(
    /**
     * ID of the status in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The plain text used to compose the status.
     */
    @SerialName("text")
    val text: String = "",

    /**
     * The plain text used to compose the status’s subject or content warning.
     */
    @SerialName("spoiler_text")
    val spoilerText: String = ""
)
