package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime

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
    val type: String = Type.Mention.value,

    /**
     * The timestamp of the notification (ISO 8601 Datetime).
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = PrecisionDateTime.Unavailable,

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
    enum class Type(val value: String) {
        Mention("mention"),
        Reblog("reblog"),
        Favourite("favourite"),
        Follow("follow")
    }
}
