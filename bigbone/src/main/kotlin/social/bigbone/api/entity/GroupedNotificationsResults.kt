package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import social.bigbone.api.method.GroupedNotificationMethods

/**
 * GroupedNotificationsResults entity.
 * @see <a href="https://docs.joinmastodon.org/methods/grouped_notifications/#GroupedNotificationsResults">GroupedNotificationsResults</a>
 * @since Mastodon 4.3.0
 */
@Serializable
data class GroupedNotificationsResults(
    /**
     * Accounts referenced by grouped notifications.
     * @since Mastodon 4.3.0
     */
    @SerialName("accounts")
    val accounts: List<Account> = emptyList(),

    /**
     * Partial accounts referenced by grouped notifications.
     * Those are only returned when requesting grouped notifications with [GroupedNotificationMethods.ExpandAccounts.PARTIAL_AVATARS].
     * @since Mastodon 4.3.0
     */
    @SerialName("partial_accounts")
    val partialAccounts: String? = null,

    /**
     * Statuses referenced by grouped notifications.
     * @since Mastodon 4.3.0
     */
    @SerialName("statuses")
    val statuses: List<Status> = emptyList(),

    /**
     * The grouped notifications themselves.
     * @since Mastodon 4.3.0
     */
    @SerialName("notification_groups")
    val notificationGroups: List<NotificationGroup>
)
