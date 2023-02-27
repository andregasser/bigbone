package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Notification
import social.bigbone.api.exception.BigBoneRequestException

/**
 * Allows access to API methods with endpoints having an "api/vX/notifications" or "api/vX/notification" prefix.
 * @see <a href="https://docs.joinmastodon.org/methods/notifications/">Mastodon notifications API methods</a>
 */
class NotificationMethods(private val client: MastodonClient) {

    /**
     * Notifications concerning the user.
     * @param range optional Range for the pageable return value
     * @param excludeTypes Types to exclude from the results. See Mastodon API documentation for details.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#get">Mastodon API documentation: methods/notifications/#get</a>
     */
    @JvmOverloads
    fun getNotifications(range: Range = Range(), excludeTypes: List<Notification.Type>? = null): MastodonRequest<Pageable<Notification>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/notifications",
            method = MastodonClient.Method.GET,
            parameters = range.toParameters().apply {
                excludeTypes?.let {
                    append("exclude_types", excludeTypes.map { it.value })
                }
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
            endpoint = "api/v1/notification/$id", // singular "notification" is correct here
            method = MastodonClient.Method.GET
        )
    }

    /**
     * Clear all notifications from the server.
     * @see <a href="https://docs.joinmastodon.org/methods/notifications/#clear">Mastodon API documentation: methods/notifications/#clear</a>
     */
    @Throws(BigBoneRequestException::class)
    fun clearNotifications() {
        client.performAction(
            endpoint = "api/v1/notifications/clear",
            method = MastodonClient.Method.POST
        )
    }
}
