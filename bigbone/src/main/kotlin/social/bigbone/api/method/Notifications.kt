package social.bigbone.api.method

import social.bigbone.MastodonClient
import social.bigbone.MastodonRequest
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Notification
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.extension.emptyRequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#notifications
 */
class Notifications(private val client: MastodonClient) {
    // GET /api/v1/notifications
    @JvmOverloads
    fun getNotifications(range: Range = Range(), excludeTypes: List<Notification.Type>? = null): MastodonRequest<Pageable<Notification>> {
        val parameter = range.toParameter()
        if (excludeTypes != null) {
            parameter.append("exclude_types", excludeTypes.map { it.value })
        }
        return MastodonRequest<Pageable<Notification>>(
            {
                client.get(
                    "api/v1/notifications",
                    parameter
                )
            },
            {
                client.getSerializer().fromJson(it, Notification::class.java)
            }
        ).toPageable()
    }

    // GET /api/v1/notifications/:id
    fun getNotification(id: Long): MastodonRequest<Notification> {
        return MastodonRequest<Notification>(
            {
                client.get("api/v1/notifications/$id")
            },
            {
                client.getSerializer().fromJson(it, Notification::class.java)
            }
        )
    }

    //  POST /api/v1/notifications/clear
    @Throws(BigboneRequestException::class)
    fun clearNotifications() {
        val response = client.post(
            "api/v1/notifications/clear",
            emptyRequestBody()
        )
        if (!response.isSuccessful) {
            throw BigboneRequestException(response)
        }
    }
}
