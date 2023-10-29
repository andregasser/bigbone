package social.bigbone.api.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import social.bigbone.api.entity.data.Alerts

@Serializable
data class WebPushSubscription(

    /**
     * The ID of the Web Push subscription in the database.
     */
    @SerialName("id")
    val id: String,


    /**
     * Where push alerts will be sent to.
     */
    @SerialName("endpoint")
    val endpoint: String,


    /**
     * The streaming server’s VAPID key.
     */
    @SerialName("server_key")
    val serverKey: String,


    /**
     * Which alerts should be delivered to the endpoint.
     */
    @SerialName("alerts")
    val alerts: Alerts
)

