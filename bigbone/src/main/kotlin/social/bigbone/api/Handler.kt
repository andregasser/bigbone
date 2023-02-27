package social.bigbone.api

import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.Status

/**
 * Used to implement a handler for streaming endpoints (e.g. [social.bigbone.api.method.StreamingMethods.federatedPublic]).
 */
interface Handler {

    fun onStatus(status: Status)

    // ignore if public streaming
    fun onNotification(notification: Notification)

    // ignore if public streaming
    fun onDelete(id: String)
}
