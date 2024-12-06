package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.Parameters
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.NotificationType
import social.bigbone.api.entity.UnreadNotificationCount
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/notifications" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/notifications/">Mastodon notifications API methods</a>
 */
class NotificationMethods(private val client: MastodonClient) {

    private val endpoint = "api/v1/notifications"

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
        includeTypes: List<NotificationType>? = null,
        excludeTypes: List<NotificationType>? = null,
        accountId: String? = null,
        range: Range = Range()
    ): MastodonRequest<Pageable<Notification>> {
        return client.getPageableMastodonRequest(
            endpoint = endpoint,
            method = MastodonClient.Method.GET,
            parameters = range.toParameters().apply {
                includeTypes?.let {
                    append("types", includeTypes.map(NotificationType::apiName))
                }
                excludeTypes?.let {
                    append("exclude_types", excludeTypes.map(NotificationType::apiName))
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
            endpoint = "$endpoint/$id",
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
            endpoint = "$endpoint/clear",
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
            endpoint = "$endpoint/$notificationId/dismiss",
            method = MastodonClient.Method.POST
        )
    }

    /**
     * Get the (capped) number of unread notification groups for the current user.
     * A notification is considered unread if it is more recent than the notifications read marker.
     * Because the count is dependent on the parameters, it is computed every time and is thus a relatively slow operation
     * (although faster than getting the full corresponding notifications), therefore the number of returned notifications is capped.
     * @param limit Maximum number of results to return. Defaults to 100 notifications. Max 1_000 notifications.
     * @param types [NotificationType]s that should count towards unread notifications.
     * @param excludeTypes [NotificationType]s that should not count towards unread notifications.
     * @param accountId Only count unread notifications received from the specified account.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#unread-count">Mastodon API documentation: methods/notifications/#unread-count</a>
     * @since Mastodon 4.3.0
     * @throws IllegalArgumentException if [limit] is set and higher than 1_000.
     */
    @Throws(IllegalArgumentException::class)
    fun getNumberOfUnreadNotifications(
        limit: Int? = null,
        types: List<NotificationType>? = null,
        excludeTypes: List<NotificationType>? = null,
        accountId: String? = null
    ): MastodonRequest<UnreadNotificationCount> {
        if (limit != null) {
            require(limit <= 1_000) { "Limit must be no larger than 1000" }
        }

        return client.getMastodonRequest(
            endpoint = "$endpoint/unread_count",
            method = MastodonClient.Method.GET,
            parameters = Parameters().apply {
                limit?.let { append("limit", limit) }
                accountId?.let { append("account_id", accountId) }
                types?.let {
                    append("types", types.map(NotificationType::apiName))
                }
                excludeTypes?.let {
                    append("exclude_types", excludeTypes.map(NotificationType::apiName))
                }
            }
        )
    }
}
