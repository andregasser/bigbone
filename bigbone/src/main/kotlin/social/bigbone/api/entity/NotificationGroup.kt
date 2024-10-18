package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.DateTimeSerializer
import social.bigbone.PrecisionDateTime
import social.bigbone.PrecisionDateTime.InvalidPrecisionDateTime

/**
 * NotificationGroup entity.
 * @see <a href="https://docs.joinmastodon.org/methods/grouped_notifications/#NotificationGroup">grouped_notifications/#NotificationGroup</a>
 * @since Mastodon 4.3.0
 */
@Serializable
data class NotificationGroup(
    /**
     * Group key identifying the grouped notifications.
     * Should be treated as an opaque value.
     * @since Mastodon 4.3.0
     */
    @SerialName("group_key")
    val groupKey: String = "",

    /**
     * Total number of individual notifications that are part of this notification group.
     * @since Mastodon 4.3.0
     */
    @SerialName("notifications_count")
    val notificationsCount: Int = 0,

    /**
     * The type of event that resulted in the notifications in this group.
     * @since Mastodon 4.3.0
     */
    @SerialName("type")
    val type: NotificationType? = null,

    /**
     * ID of the most recent notification in the group.
     * @since Mastodon 4.3.0
     */
    @SerialName("most_recent_notification_id")
    val mostRecentNotificationId: Int = 0,

    /**
     *  ID of the oldest notification from this group represented within the current page.
     *  This is only returned when paginating through notification groups.
     *  Useful when polling new notifications.
     * @since Mastodon 4.3.0
     */
    @SerialName("page_min_id")
    val pageMinId: String? = null,

    /**
     * ID of the newest notification from this group represented within the current page.
     * This is only returned when paginating through notification groups.
     * Useful when polling new notifications.
     * @since Mastodon 4.3.0
     */
    @SerialName("page_max_id")
    val pageMaxId: String? = null,

    /**
     * Date at which the most recent notification from this group within the current page has been created.
     * This is only returned when paginating through notification groups.
     * @since Mastodon 4.3.0
     */
    @SerialName("latest_page_notification_at")
    @Serializable(with = DateTimeSerializer::class)
    val latestPageNotificationAt: PrecisionDateTime = InvalidPrecisionDateTime.Unavailable,

    /**
     *  IDs of some of the accounts who most recently triggered notifications in this group.
     * @since Mastodon 4.3.0
     */
    @SerialName("sample_account_ids")
    val sampleAccountIds: List<String> = emptyList(),

    /**
     * ID of the [Status] that was the object of the notification.
     * Attached when [type] of the notification is
     * * [NotificationType.FAVOURITE],
     * * [NotificationType.REBLOG],
     * * [NotificationType.STATUS],
     * * [NotificationType.MENTION],
     * * [NotificationType.POLL], or
     * * [NotificationType.UPDATE].
     * @since Mastodon 4.3.0
     */
    @SerialName("status_id")
    val statusId: String? = null,

    /**
     * Report that was the object of the notification.
     * Attached when type of the notification is [NotificationType.ADMIN_REPORT].
     * @since Mastodon 4.3.0
     */
    @SerialName("report")
    val report: Report? = null,

    /**
     * Summary of the event that caused follow relationships to be severed.
     * Attached when [type] of the notification is [NotificationType.SEVERED_RELATIONSHIPS].
     * @since Mastodon 4.3.0
     */
    @SerialName("event")
    val event: RelationshipSeveranceEvent? = null,

    /**
     * Moderation warning that caused the notification.
     * Attached when [type] of the notification is [NotificationType.MODERATION_WARNING].
     * @since Mastodon 4.3.0
     */
    @SerialName("moderation_warning")
    val moderationWarning: AccountWarning? = null
)
