package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Notification
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/notifications" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/notifications/">Mastodon notifications API methods</a>
 */
class NotificationMethods(private val client: MastodonClient) {
    private val notificationsEndpoint = "api/v1/notifications"

    /**
     * Notifications concerning the user.
     * @param includeTypes Types to include in the results.
     * @param excludeTypes Types to exclude from the results.
     * @param accountId Return only notifications received from the specified account.
     * @param range optional Range for the pageable return value.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#get">Mastodon API documentation: methods/notifications/#get</a>
     */
    @JvmOverloads
    fun getAllNotifications(
        includeTypes: List<Notification.NotificationType>? = null,
        excludeTypes: List<Notification.NotificationType>? = null,
        accountId: String? = null,
        range: Range = Range()
    ): MastodonRequest<Pageable<Notification>> {
        return client.getPageableMastodonRequest(
            endpoint = notificationsEndpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters().apply {
                includeTypes?.let {
                    append("types", includeTypes.map(Notification.NotificationType::apiName))
                }
                excludeTypes?.let {
                    append("exclude_types", excludeTypes.map(Notification.NotificationType::apiName))
                }
                accountId?.let { append("account_id", accountId) }
            }
        )
    }

    /**
     * View information about a notification with a given ID.
     * @param id ID of the notification to view
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#get-one">Mastodon API documentation: methods/notifications/#get-one</a>
     */
    fun getNotification(id: String): MastodonRequest<Notification> {
        return client.getMastodonRequest(
            endpoint = "$notificationsEndpoint/$id",
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Clear all notifications from the server.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#clear">Mastodon API documentation: methods/notifications/#clear</a>
     */
    @Throws(BigBoneRequestException::class)
    fun dismissAllNotifications() {
        client.performAction(
            endpoint = "$notificationsEndpoint/clear",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Dismiss a single notification from the server.
     * @param notificationId The ID of the Notification in the database to be deleted.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#dismiss">Mastodon API documentation: methods/notifications/#dismiss</a>
     */
    @Throws(BigBoneRequestException::class)
    fun dismissNotification(notificationId: String) {
        client.performAction(
            endpoint = "$notificationsEndpoint/$notificationId/dismiss",
            method = MastodonClient.Method.POST
        )
    }
}
