package social.bigbone.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Capped count of unread notifications.
 * @since Mastodon 4.3.0
 */
@Serializable
data class UnreadNotificationCount(
    @SerialName("count")
    val count: Int
)
