package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime

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
    val type: NotificationType? = null,

    /**
     * The timestamp of the notification.
     */
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,

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
    val report: Report? = null,

    /**
     * Group key shared by similar notifications, to be used in the grouped notifications feature.
     * Should be considered opaque, but ungrouped notifications can be assumed to have a group_key of the form "ungrouped-{notification_id}".
     * @since Mastodon 4.3.0
     */
    @SerialName("group_key")
    val groupKey: String? = null,

    /**
     * Summary of the event that caused follow relationships to be severed.
     * Attached when [type] of the notification is [NotificationType.SEVERED_RELATIONSHIPS].
     * @since Mastodon 4.3.0
     */
    @SerialName("relationship_severance_event")
    val relationshipSeveranceEvent: RelationshipSeveranceEvent? = null
)
