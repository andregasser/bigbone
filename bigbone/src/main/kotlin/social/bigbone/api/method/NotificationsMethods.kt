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
class NotificationsMethods(private val client: MastodonClient) {
    // GET /api/v1/notifications
    @JvmOverloads
    fun getNotifications(range: Range = Range(), excludeTypes: List<Notification.Type>? = null): MastodonRequest<Pageable<Notification>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/notifications",
            method = MastodonClient.Method.GET,
            parameters = range.toParameterList().apply {
                excludeTypes?.let {
                    append("exclude_types", excludeTypes.map { it.value })
                }
            }
        )
    }

    // GET /api/v1/notifications/:id
    fun getNotification(id: String): MastodonRequest<Notification> {
        return client.getMastodonRequest(
            endpoint = "api/v1/notification/$id", // singular "notification" is correct here
            method = MastodonClient.Method.GET
        )
    }

    //  POST /api/v1/notifications/clear
    @Throws(BigBoneRequestException::class)
    fun clearNotifications() {
        client.performAction(
            endpoint = "api/v1/notifications/clear",
            method = MastodonClient.Method.POST
        )
    }
}
