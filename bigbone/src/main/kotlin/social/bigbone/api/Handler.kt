package social.bigbone.api

import social.bigbone.api.entity.Notification
import social.bigbone.api.entity.Status

interface Handler {

    fun onStatus(status: Status)

    //ignore if public streaming
    fun onNotification(notification: Notification)

    //ignore if public streaming
    fun onDelete(id: Long)
}
