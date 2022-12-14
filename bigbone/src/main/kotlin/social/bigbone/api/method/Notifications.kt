package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Notification
import social.bigbone.api.exception.BigboneRequestException

class Notifications(private val client: MastodonClient) {
    // GET /api/v1/notifications
    @JvmOverloads
    fun getNotifications(range: Range = Range(), excludeTypes: List<Notification.Type>? = null): MastodonRequest<Pageable<Notification>> {
        return client.getPageableMastodonRequest(
            endpoint = "api/v1/notifications",
            method = MastodonClient.Method.GET,
            parameters = range.toParameter().apply {
                excludeTypes?.let {
                    append("exclude_types", excludeTypes.map { it.value })
                }
            }
        )
    }

    // GET /api/v1/notifications/:id
    fun getNotification(id: String): MastodonRequest<Notification> {
        return client.getMastodonRequest(
            endpoint = "api/v1/notifications/$id",
            method = MastodonClient.Method.GET
        )
    }

    //  POST /api/v1/notifications/clear
    @Throws(BigboneRequestException::class)
    fun clearNotifications() {
        client.performAction(
            endpoint = "api/v1/notifications/clear",
            method = MastodonClient.Method.POST
        )
    }
}
