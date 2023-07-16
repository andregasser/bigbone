package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a conversation with "direct message" visibility.
 * @see <a href="https://docs.joinmastodon.org/entities/Conversation/">Mastodon API Conversation</a>
 */
data class Conversation(
    /**
     * The ID of the conversation in the database.
     */
    @SerializedName("id")
    val id: String? = null,

    /**
     * Is the conversation currently marked as unread?
     */
    @SerializedName("unread")
    val unread: Boolean? = null,

    /**
     * Participants in the conversation.
     */
    @SerializedName("accounts")
    val accounts: List<Account>? = null,

    /**
     * The last status in the conversation.
     */
    @SerializedName("last_status")
    val lastStatus: Status? = null,
)
