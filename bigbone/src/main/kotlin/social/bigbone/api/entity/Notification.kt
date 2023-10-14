package social.bigbone.api.entity

import com.google.gson.annotations.SerializedName

/**
 * Represents a notification of an event relevant to the user.
 * @see <a href="https://docs.joinmastodon.org/entities/Notification/">Mastodon API Notification</a>
 */
data class Notification(
    /**
     * The id of the notification in the database.
     */
    @SerializedName("id")
    val id: String = "0",

    /**
     * The type of event that resulted in the notification.
     */
    @SerializedName("type")
    val type: String = Type.Mention.value,

    /**
     * The timestamp of the notification (ISO 8601 Datetime).
     */
    @SerializedName("created_at")
    val createdAt: String = "",

    /**
     * The account that performed the action that generated the notification.
     */
    @SerializedName("account")
    val account: Account? = null,

    /**
     * Status that was the object of the notification. Attached when type of the notification is favourite, reblog, status, mention, poll, or update.
     */
    @SerializedName("status")
    val status: Status? = null,

    /**
     * Report that was the object of the notification. Attached when type of the notification is admin.report.
     */
    @SerializedName("report")
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
