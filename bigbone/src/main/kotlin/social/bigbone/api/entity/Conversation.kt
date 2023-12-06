package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a conversation with "direct message" visibility.
 * @see <a href="https://docs.joinmastodon.org/entities/Conversation/">Mastodon API Conversation</a>
 */
@Suppress("ktlint:standard:no-blank-line-in-list")
@Serializable
data class Conversation(
    /**
     * The ID of the conversation in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * Is the conversation currently marked as unread?
     */
    @SerialName("unread")
    val unread: Boolean = true,

    /**
     * Participants in the conversation.
     */
    @SerialName("accounts")
    val accounts: List<Account>? = emptyList(),

    /**
     * The last status in the conversation.
     */
    @SerialName("last_status")
    val lastStatus: Status? = null
)
