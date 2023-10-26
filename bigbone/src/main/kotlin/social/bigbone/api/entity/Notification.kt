package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a notification of an event relevant to the user.
 * @see <a href="https://docs.joinmastodon.org/entities/Notification/">Mastodon API Notification</a>
 */
@Serializable
data class Notification(
    /**
     * The id of the notification in the database.
     */
    @SerialName("id")
    val id: String = "0",

    /**
     * The type of event that resulted in the notification.
     */
    @SerialName("type")
    val type: Type = Type.MENTION,

    /**
     * The timestamp of the notification (ISO 8601 Datetime).
     */
    @SerialName("created_at")
    val createdAt: String = "",

    /**
     * The account that performed the action that generated the notification.
     */
    @SerialName("account")
    val account: Account? = null,

    /**
     * Status that was the object of the notification. Attached when type of the notification is favourite, reblog, status, mention, poll, or update.
     */
    @SerialName("status")
    val status: Status? = null,

    /**
     * Report that was the object of the notification. Attached when type of the notification is admin.report.
     */
    @SerialName("report")
    val report: Report? = null
) {
    /**
     * Specifies the notification type.
     */
    @Serializable
    enum class Type {
        @SerialName("mention")
        MENTION,

        @SerialName("reblog")
        REBLOG,

        @SerialName("favourite")
        FAVOURITE,

        @SerialName("follow")
        FOLLOW
    }
}
